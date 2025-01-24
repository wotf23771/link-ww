package link.ww.base.manager;

import link.common.spring.SpringApplicationContext;
import link.common.utils.HttpUtils;
import link.common.utils.JsonUtils;
import link.common.utils.UrlUtils;
import link.ww.base.BaseProperties;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class BaseManager {

  public <T> T get(String uri, Map<String, Object> queryParams, Class<T> clazz) {
    BaseProperties baseProperties = SpringApplicationContext.getBean(BaseProperties.class);
    String url = baseProperties.getBaseUrl() + uri;
    if (queryParams == null) {
      queryParams = new HashMap<>();
    }
    url = UrlUtils.build(url, queryParams);
    HttpUtils.HttpResult result = HttpUtils.get(url);
    if (result.isSuccess()) {
      return JsonUtils.fromJson(result.getBody(), clazz);
    } else {
      log.error(result.getBody());
      return null;
    }
  }

}
