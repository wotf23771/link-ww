package link.ww.third.cache;

import link.redis.cache.CacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class SuiteTicketCache {

  private static final String cacheName = "ww:third:suite_ticket";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
  }

  public void put(String suiteId, String ticket) {
    if (StringUtils.isBlank(suiteId) || StringUtils.isBlank(ticket)) {
      return;
    }
    getCache().put(suiteId, ticket);
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
