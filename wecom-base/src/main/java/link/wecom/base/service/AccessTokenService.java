package link.wecom.base.service;

import link.common.utils.StringUtils;
import link.wecom.base.BaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccessTokenService {

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
