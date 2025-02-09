package link.ww.third.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

/**
 * 服务商token
 */
@Component
public class ProviderTokenCache {

  private static final String cacheName = "ww:third:provider_token";

  @Autowired
  private CacheManager cacheManager;

  private Cache getCache() {
    return cacheManager.getCache(cacheName);
  }

  public void put(String corpId, String token) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(token)) {
      return;
    }
    getCache().put(corpId, token);
  }

  public String get(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return null;
    }
    return getCache().get(corpId, () -> null);
  }

  public void delete(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return;
    }
    getCache().evict(corpId);
  }

}
