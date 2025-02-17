package link.ww.local.service;

import link.common.exception.BaseException;
import link.common.utils.StringUtils;
import link.ww.base.BaseProperties;
import link.ww.local.Agent;
import link.ww.local.cache.AgentTokenCache;
import link.ww.local.manager.AccessTokenManager;
import link.ww.local.manager.AccessTokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业微信应用凭证服务
 * 管理企业微信应用的access_token获取和缓存
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
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

  /**
   * 获取应用的access_token
   * 优先从缓存获取，缓存不存在则重新请求API
   *
   * @param agentName 应用标识
   * @return access_token
   */
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
