package link.ww.agent.web;

import link.web.annotation.WrapperWebResult;
import link.ww.base.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 认证控制类
 *
 * @author wangxiaolei
 * @since 2025/2/11 18:28
 */
@WrapperWebResult
@Slf4j
@RestController
@RequestMapping
public class AuthorizeAction {

  @Autowired
  private AuthorizeService authorizeService;

  @GetMapping("#{agentProperties.auth.requestAuthorizeUri}")
  public String url(String redirectUrl) {
    String state = UUID.randomUUID().toString();
    return authorizeService.getAuthorizeUrl(redirectUrl, state);
  }

  @GetMapping("#{agentProperties.auth.requestWebAuthorizeUri}")
  public String webUrl(String redirectUrl) {
    String state = UUID.randomUUID().toString();
    return authorizeService.getWebAuthorizeUrl(redirectUrl, state);
  }

}
