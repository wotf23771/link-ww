package link.ww.third.web;

import link.common.spring.SpringApplicationContext;
import link.common.utils.XmlUtils;
import link.ww.base.BaseProperties;
import link.ww.base.aes.AesException;
import link.ww.base.aes.WXBizMsgCrypt;
import link.ww.base.event.CommandCallbackEvent;
import link.ww.base.event.DataCallbackEvent;
import link.ww.third.ThirdProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("#{thirdProperties.callbackPrefix}")
public class SuiteCallbackAction {

  private static final String ERROR = "error";

  private static final String SUCCESS = "success";

  @Autowired
  private ThirdProperties thirdProperties;

  @Autowired
  private BaseProperties baseProperties;

  /**
   * 数据回调，验证
   */
  @GetMapping("/data")
  public String dataGet(String msg_signature, String timestamp, String nonce, String echostr) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = thirdProperties.getToken();
      String aseKey = thirdProperties.getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }

    //需要返回的明文
    String echoStr;
    try {
      echoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
      log.debug("数据回调，验证：{}", echoStr);
    } catch (Exception e) {
      //验证URL失败，错误原因请查看异常
      e.printStackTrace();
      return ERROR;
    }
    return echoStr;
  }

  /**
   * 数据回调，接收数据
   */
  @PostMapping("/data")
  public String dataPost(String msg_signature, String timestamp, String nonce, @RequestBody String body) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = thirdProperties.getToken();
      String aseKey = thirdProperties.getEncodingAesKey();
      String toUserName = XmlUtils.getFirstTagContent(body, "ToUserName");
      wxcpt = new WXBizMsgCrypt(token, aseKey, toUserName);
    } catch (AesException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (SAXException e) {
      throw new RuntimeException(e);
    }
    String echoStr;
    try {
      echoStr = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, body);
      log.debug("数据回调，接收数据：{}", echoStr);
      DataCallbackEvent dataCallbackEvent = new DataCallbackEvent(this, echoStr);
      SpringApplicationContext.publishEvent(dataCallbackEvent);
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR;
    }
    return SUCCESS;
  }

  /**
   * 指令回调，验证
   */
  @GetMapping("/command")
  public String commandGet(String msg_signature, String timestamp, String nonce, String echostr) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = thirdProperties.getToken();
      String aseKey = thirdProperties.getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }

    //需要返回的明文
    String echoStr;
    try {
      echoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
      log.debug("指令回调，验证：{}", echoStr);
    } catch (Exception e) {
      //验证URL失败，错误原因请查看异常
      e.printStackTrace();
      return ERROR;
    }
    return echoStr;
  }

  /**
   * 指令回调，接收数据
   */
  @PostMapping("/command")
  public String commandPost(String msg_signature, String timestamp, String nonce, @RequestBody String body) {
    WXBizMsgCrypt wxcpt = null;
    try {
      String token = thirdProperties.getToken();
      String aseKey = thirdProperties.getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, thirdProperties.getSuiteId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }
    String echoStr;
    try {
      echoStr = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, body);
      log.debug("指令回调，接收数据：{}", echoStr);
      CommandCallbackEvent callbackEvent = new CommandCallbackEvent(this, echoStr);
      SpringApplicationContext.publishEvent(callbackEvent);
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR;
    }
    return SUCCESS;
  }

}
