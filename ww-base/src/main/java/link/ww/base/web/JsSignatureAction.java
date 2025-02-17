package link.ww.base.web;

import link.web.annotation.WrapperWebResult;
import link.ww.base.service.JsTicketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * JS-SDK使用权限签名
 *
 * @author wangxiaolei
 * @since 2025/2/14 13:52
 */
@WrapperWebResult
@Slf4j
@RestController
@RequestMapping
public class JsSignatureAction {

  @Autowired
  private JsTicketService jsTicketService;

  /**
   * 获取企业微信的企业签名
   *
   * @param url 需要签名的URL
   * @return JsSignature 对象，包含时间戳、随机串和签名
   */
  @GetMapping("#{wwBaseProperties.corpSignatureUri}")
  public JsSignature getCorpSignature(String url) {
    String ticket = jsTicketService.getCorpJsTicket(); // 获取企业的jsapi_ticket
    return build(ticket, url); // 构建签名
  }

  /**
   * 获取企业微信的应用签名
   *
   * @param url 需要签名的URL
   * @return JsSignature 对象，包含时间戳、随机串和签名
   */
  @GetMapping("#{wwBaseProperties.agentSignatureUri}")
  public JsSignature getAgentSignature(String url) {
    String ticket = jsTicketService.getAgentJsTicket(); // 获取应用的jsapi_ticket
    return build(ticket, url); // 构建签名
  }

  /**
   * 构建签名
   *
   * @param ticket jsapi_ticket
   * @param url    需要签名的URL
   * @return JsSignature 对象，包含时间戳、随机串和签名
   */
  private JsSignature build(String ticket, String url) {
    String nonceStr = RandomStringUtils.secure().nextAlphanumeric(10); // 生成随机字符串
    Long timestamp = System.currentTimeMillis() / 1000; // 获取当前时间戳
    StringBuilder strBuild = new StringBuilder();
    strBuild.append("jsapi_ticket=").append(ticket)
        .append("&noncestr=").append(nonceStr)
        .append("&timestamp=").append(timestamp)
        .append("&url=").append(url);
    String signature = DigestUtils.sha1Hex(strBuild.toString()); // 生成签名
    return JsSignature.builder()
        .timestamp(timestamp)
        .nonceStr(nonceStr)
        .signature(signature)
        .build(); // 返回签名对象
  }

}
