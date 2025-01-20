package link.wecom.base.service;

import link.common.exception.BaseException;
import link.common.utils.StringUtils;
import link.wecom.base.BaseProperties;
import link.wecom.base.cache.AccessTokenCache;
import link.wecom.base.manager.AccessTokenManager;
import link.wecom.base.manager.AccessTokenResponse;
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
  private AccessTokenCache accessTokenCache;

  public String getAgentToken(String agent) {
    String token = accessTokenCache.get(agent);
    if (StringUtils.isBlank(token)) {
      String corpId = baseProperties.getCorpId();
      String agentSecret = baseProperties.getAgents().get(agent);
      if (StringUtils.isBlank(agentSecret)) {
        throw new BaseException("agent [" + agent + "] not exists");
      }
      AccessTokenResponse response = accessTokenManager.getAccessToken(corpId, agentSecret);
      if (response == null) {
        return null;
      }
      accessTokenCache.put(agent, response.getAccessToken());
      token = response.getAccessToken();
    }
    return token;
  }

}
