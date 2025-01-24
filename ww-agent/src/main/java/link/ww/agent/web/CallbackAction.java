package link.ww.agent.web;

import link.ww.agent.Agent;
import link.ww.agent.aes.AesException;
import link.ww.agent.aes.WXBizMsgCrypt;
import link.ww.agent.service.AgentService;
import link.ww.base.BaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.StringReader;

@Slf4j
@RestController
@RequestMapping("#{agentProperties.callbackPrefix}")
public class CallbackAction {

  private static final String ERROR = "error";

  private static final String SUCCESS = "success";

  @Autowired
  private AgentService agentService;

  @Autowired
  private BaseProperties baseProperties;

  /**
   * 数据回调，验证
   */
  @GetMapping("/{agentId}")
  public String get(@PathVariable("agentId") String agentId, String msg_signature, String timestamp, String nonce, String echostr) {
    WXBizMsgCrypt wxcpt = null;
    try {
      Agent agent = agentService.getAgentById(agentId);
      if (agent == null) {
        return ERROR;
      }
      String token = agent.getToken();
      String aseKey = agent.getEncodingAESKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }

    //需要返回的明文
    String echoStr;
    try {
      echoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
      log.debug("返回的明文：{}", echoStr);
    } catch (Exception e) {
      //验证URL失败，错误原因请查看异常
      e.printStackTrace();
      return ERROR;
    }
    return echoStr;
  }

  /**
   * 指令回调，数据
   */
  @PostMapping("/{agentId}")
  public String post(@PathVariable("agentId") String agentId, String msg_signature, String timestamp, String nonce, @RequestBody String body) {
    WXBizMsgCrypt wxcpt = null;
    try {
      Agent agent = agentService.getAgentById(agentId);
      if (agent == null) {
        return ERROR;
      }
      String token = agent.getToken();
      String aseKey = agent.getEncodingAESKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }
    String echoStr;
    try {
      echoStr = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, body);
      log.info("post请求的明文：{}", echoStr);
      DocumentBuilder db = getDocumentBuilder();
      StringReader sr = new StringReader(echoStr);
      InputSource is = new InputSource(sr);
      Document document = db.parse(is);
      Element root = document.getDocumentElement();
      String infoType = getElementContent(root, "InfoType");
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR;
    }
    return SUCCESS;
  }

  private String getElementContent(Element root, String elementName) {
    if (root == null) {
      return null;
    }
    NodeList infoTypeList = root.getElementsByTagName(elementName);
    if (infoTypeList.getLength() > 0) {
      return infoTypeList.item(0).getTextContent();
    }
    return null;
  }

  private String getElementContent(String xml, String elementName) {
    String content = null;
    try {
      DocumentBuilder db = getDocumentBuilder();
      StringReader sr = new StringReader(xml);
      InputSource is = new InputSource(sr);
      Document document = db.parse(is);
      Element root = document.getDocumentElement();
      content = getElementContent(root, elementName);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return content;
  }

  private DocumentBuilder getDocumentBuilder() {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      return dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

}
