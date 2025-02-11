package link.ww.third.cache;

import link.redis.cache.CacheFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

/**
 * 企业凭证缓存管理
 * 用于管理企业的access_token，该token用于调用企业微信API时的鉴权
 * 
 * <p>缓存key格式: ww:third:corp_token:{corpId}
 * <p>缓存值: 企业的access_token
 * <p>缓存时间: 7200秒
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class CorpTokenCache {

  private static final String cacheName = "ww:third:corp_token";

  private Cache getCache() {
    return CacheFactory.getCache(cacheName);
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
    return getCache().get(corpId, () -> "");
  }

  public void delete(String corpId) {
    if (StringUtils.isBlank(corpId)) {
      return;
    }
    getCache().evict(corpId);
  }

}
