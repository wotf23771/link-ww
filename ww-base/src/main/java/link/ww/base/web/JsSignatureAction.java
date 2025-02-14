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

  @GetMapping("#{wwBaseProperties.corpSignatureUri}")
  public JsSignature getCorpSignature(String url) {
    String ticket = jsTicketService.getCorpJsTicket();
    return build(ticket, url);
  }

  @GetMapping("#{wwBaseProperties.agentSignatureUri}")
  public JsSignature getAgentSignature(String url) {
    String ticket = jsTicketService.getAgentJsTicket();
    return build(ticket, url);
  }

  private JsSignature build(String ticket, String url) {
    String nonceStr = RandomStringUtils.randomAlphanumeric(10);
    Long timestamp = System.currentTimeMillis() / 1000;
    StringBuilder strBuild = new StringBuilder();
    strBuild.append("jsapi_ticket=").append(ticket)
        .append("&noncestr=").append(nonceStr)
        .append("&timestamp=").append(timestamp)
        .append("&url=").append(url);
    String signature = DigestUtils.sha1Hex(strBuild.toString());
    return JsSignature.builder()
        .timestamp(timestamp)
        .nonceStr(nonceStr)
        .signature(signature)
        .build();
  }

}
