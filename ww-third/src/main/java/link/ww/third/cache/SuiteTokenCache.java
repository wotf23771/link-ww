package link.ww.third.cache;

import link.redis.cache.CacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class SuiteTokenCache {

  private static final String cacheName = "ww:third:suite_token";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
  }

  public void put(String suiteId, String token) {
    if (StringUtils.isBlank(suiteId) || StringUtils.isBlank(token)) {
      return;
    }
    getCache().put(suiteId, token);
  }

  public String get(String suiteId) {
    if (StringUtils.isBlank(suiteId)) {
      return null;
    }
    return getCache().get(suiteId, () -> "");
  }

  public void delete(String suiteId) {
    if (StringUtils.isBlank(suiteId)) {
      return;
    }
    getCache().evict(suiteId);
  }

}
