package link.ww.third.service;

import link.ww.base.service.AccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "link.ww.base.agent-type", havingValue = "third")
public class ThirdTokenService implements AccessTokenService, InitializingBean {

  @Override
  public String getAccessToken() {
    return "";
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AccessTokenService is [{}]", ThirdTokenService.class.getName());
  }

}
