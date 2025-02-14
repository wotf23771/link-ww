package link.ww.agent.cache;

import link.common.utils.StringUtils;
import link.redis.cache.CacheFactory;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

/**
 * 企业的jsapi_ticket缓存
 *
 * @author wangxiaolei
 * @since 2025/2/14
 */
@Service
public class CorpJsTicketCache {

  private static final String cacheName = "ww:jsapi_ticket:corp";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
  }

  public void put(String corpId, String value) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(value)) {
      return;
    }
    getCache().put(corpId, value);
  }

  public String get(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return null;
    }
    return getCache().get(corpId, () -> "");
  }

  public void delete(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return;
    }
    getCache().evict(corpId);
  }

}
