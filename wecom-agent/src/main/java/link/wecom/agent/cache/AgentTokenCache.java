package link.wecom.agent.cache;

import link.redis.cache.CacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

@Service
public class AgentTokenCache {

  private static final String cacheName = "wecom:token";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
  }

  public void put(String agent, String value) {
    if (StringUtils.isBlank(agent) || StringUtils.isBlank(value)) {
      return;
    }
    getCache().put(agent, value);
  }

  public String get(String agent) {
    if (StringUtils.isBlank(agent)) {
      return null;
    }
    return getCache().get(agent, () -> "");
  }

  public void delete(String agent) {
    if (StringUtils.isBlank(agent)) {
      return;
    }
    getCache().evict(agent);
  }

}
