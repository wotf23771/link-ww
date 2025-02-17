package link.ww.local.service;

import link.common.utils.StringUtils;
import link.ww.base.BaseProperties;
import link.ww.base.service.AccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultTokenService implements AccessTokenService, InitializingBean {

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private AgentTokenService agentTokenService;

  public String getAccessToken() {
    String defaultAgent = baseProperties.getDefaultAgent();
    if (StringUtils.isBlank(defaultAgent)) {
      return null;
    }
    return agentTokenService.getAgentToken(defaultAgent);
  }

  @Override
  public void afterPropertiesSet() {
    log.debug("AccessTokenService is [{}]", DefaultTokenService.class.getName());
  }

}
