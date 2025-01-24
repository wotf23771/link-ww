package link.ww.msg.audit.web;

import link.ww.base.BaseProperties;
import link.ww.msg.audit.MsgAuditProperties;
import link.ww.msg.audit.aes.AesException;
import link.ww.msg.audit.aes.WXBizMsgCrypt;
import link.ww.msg.audit.service.AccessTokenService;
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

/**
 * 回调通知
 *
 * @author wangxiaolei
 * @since 2023/1/9 14:43
 */
@Slf4j
@RestController
@RequestMapping("/msg/audit/callback")
public class CallbackAction {

  private static final String ERROR = "error";

  private static final String SUCCESS = "success";

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private MsgAuditProperties msgAuditProperties;

  /**
   * 数据回调，验证
   */
  @GetMapping
  public String get(String msg_signature, String timestamp, String nonce, String echostr) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = msgAuditProperties.getEventSetting().getToken();
      String aseKey = msgAuditProperties.getEventSetting().getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }

    //需要返回的明文
    String echoStr;
    try {
      echoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
      log.info("返回的明文：{}", echoStr);
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
  @PostMapping
  public String post(String msg_signature, String timestamp, String nonce, @RequestBody String body) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = msgAuditProperties.getEventSetting().getToken();
      String aseKey = msgAuditProperties.getEventSetting().getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }
    String echoStr;
    try {
//      log.info("post请求的密文：{}", body);
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
