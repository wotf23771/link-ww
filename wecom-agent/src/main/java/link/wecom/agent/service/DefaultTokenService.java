package link.wecom.agent.service;

import link.common.utils.StringUtils;
import link.wecom.base.BaseProperties;
import link.wecom.base.service.AccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultTokenService implements AccessTokenService {

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

}
