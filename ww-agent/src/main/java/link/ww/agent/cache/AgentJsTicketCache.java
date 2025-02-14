package link.ww.agent.cache;

import link.common.utils.StringUtils;
import link.redis.cache.CacheFactory;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

/**
 * 应用的jsapi_ticket缓存
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:30
 */
@Service
public class AgentJsTicketCache {

  private static final String cacheName = "ww:jsapi_ticket:corp";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
  }

  private String getKey(String corpId, String agentId) {
    return corpId + "_" + agentId;
  }

  public void put(String corpId, String agentId, String value) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(agentId) || StringUtils.isBlank(value)) {
      return;
    }
    getCache().put(getKey(corpId, agentId), value);
  }

  public String get(String corpId, String agentId) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(agentId)) {
      return null;
    }
    return getCache().get(getKey(corpId, agentId), () -> "");
  }

  public void delete(String corpId, String agentId) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(agentId)) {
      return;
    }
    getCache().evict(getKey(corpId, agentId));
  }

}
