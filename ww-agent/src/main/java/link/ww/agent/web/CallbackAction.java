package link.ww.agent.web;

import link.common.spring.SpringApplicationContext;
import link.ww.agent.Agent;
import link.ww.agent.service.AgentService;
import link.ww.base.BaseProperties;
import link.ww.base.aes.AesException;
import link.ww.base.aes.WXBizMsgCrypt;
import link.ww.base.event.EventCallbackEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 企业微信回调接口
 * 处理企业微信的回调请求，包括URL验证和数据接收
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
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
   * 处理URL验证请求
   * 验证企业微信回调配置的有效性
   *
   * @param agentId 应用ID
   * @param msg_signature 消息签名
   * @param timestamp 时间戳
   * @param nonce 随机串
   * @param echostr 随机字符串
   * @return 解密后的echostr
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
      String aseKey = agent.getEncodingAesKey();
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
      String aseKey = agent.getEncodingAesKey();
      wxcpt = new WXBizMsgCrypt(token, aseKey, baseProperties.getCorpId());
    } catch (AesException e) {
      throw new RuntimeException(e);
    }
    String echoStr;
    try {
      echoStr = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, body);
      log.debug("post请求的明文：{}", echoStr);
      EventCallbackEvent eventCallbackEvent = new EventCallbackEvent(this, echoStr);
      SpringApplicationContext.publishEvent(eventCallbackEvent);
    } catch (Exception e) {
      e.printStackTrace();
      return ERROR;
    }
    return SUCCESS;
  }

}
