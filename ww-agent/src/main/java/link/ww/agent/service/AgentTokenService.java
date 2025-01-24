package link.ww.agent.service;

import link.common.exception.BaseException;
import link.common.utils.StringUtils;
import link.ww.agent.Agent;
import link.ww.agent.cache.AgentTokenCache;
import link.ww.agent.manager.AccessTokenManager;
import link.ww.agent.manager.AccessTokenResponse;
import link.ww.base.BaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AgentTokenService {

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private AccessTokenManager accessTokenManager;

  @Autowired
  private AgentTokenCache accessTokenCache;

  @Autowired
  private AgentService agentService;

  public String getAgentToken(String agentName) {
    String token = accessTokenCache.get(agentName);
    if (StringUtils.isBlank(token)) {
      String corpId = baseProperties.getCorpId();
      Agent agent = agentService.getAgentByName(agentName);
      if (agent == null) {
        throw new BaseException("agent [" + agentName + "] not exists");
      }
      AccessTokenResponse response = accessTokenManager.getAccessToken(corpId, agent.getSecret());
      if (response == null) {
        return null;
      }
      accessTokenCache.put(agentName, response.getAccessToken());
      token = response.getAccessToken();
    }
    return token;
  }

}
