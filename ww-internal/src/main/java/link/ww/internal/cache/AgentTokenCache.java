package link.ww.internal.cache;

import link.redis.cache.CacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

/**
 * 企业微信应用凭证缓存
 * 管理应用access_token的缓存
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Service
public class AgentTokenCache {

  private static final String cacheName = "ww:token";

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
