package link.ww.agent.service;

import link.ww.base.AuthorizeScope;
import link.ww.base.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "link.ww.base.agent-type", havingValue = "inner")
public class AgentAuthService implements AuthorizeService, InitializingBean {

  @Override
  public String getAuthorizeUrl(String state) {
    return getAuthorizeUrl(state, AuthorizeScope.BASE);
  }

  public String getAuthorizeUrl(String state, AuthorizeScope scope) {
    return "redirect:" + state;
  }

  @Override
  public String getWebAuthorizeUrl(String state) {
    return getWebAuthorizeUrl(state, AuthorizeScope.BASE);
  }

  public String getWebAuthorizeUrl(String state, AuthorizeScope scope) {
    return "redirect:" + state;
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", AgentAuthService.class.getName());
  }

}
