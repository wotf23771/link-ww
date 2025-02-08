package link.ww.third.service;

import link.ww.base.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "link.ww.base.agent-type", havingValue = "third")
public class ThirdAuthService implements AuthorizeService, InitializingBean {

  @Override
  public String getAuthorizeUrl(String state) {
    return "";
  }

  @Override
  public String getWebAuthorizeUrl(String state) {
    return "";
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AuthorizeService is [{}]", ThirdAuthService.class.getName());
  }

}
