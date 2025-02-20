package link.ww.base.manager;

import link.common.spring.SpringApplicationContext;
import link.common.utils.HttpUtils;
import link.common.utils.JsonUtils;
import link.common.utils.UrlUtils;
import link.ww.base.BaseProperties;
import link.ww.base.domain.BaseResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 企业微信API基础管理器
 * 提供HTTP请求封装，统一处理企业微信API的调用
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
public abstract class BaseManager {

  /**
   * 执行POST请求
   *
   * @param uri 接口路径
   * @param queryParams 查询参数
   * @param requestBody 请求体
   * @param clazz 返回值类型
   * @return API响应结果
   */
  public <T> T executePost(String uri, Map<String, Object> queryParams, String requestBody, Class<T> clazz) {
    BaseProperties baseProperties = SpringApplicationContext.getBean(BaseProperties.class);
    String url = baseProperties.getBaseUrl() + uri;
    if (queryParams == null) {
      queryParams = new HashMap<>();
    }
    url = UrlUtils.build(url, queryParams);
    HttpUtils.HttpResult result = HttpUtils.postJson(url, requestBody);
    if (result.isSuccess()) {
      String responseBody = result.getBody();
      BaseResponse baseResponse = JsonUtils.fromJson(responseBody, BaseResponse.class);
      if (baseResponse == null) {
        log.error(buildError(uri, queryParams, responseBody));
        return null;
      }
      if (Objects.equals(baseResponse.getErrCode(), 0)) {
        log.debug(responseBody);
        return JsonUtils.fromJson(responseBody, clazz);
      } else {
        log.error(buildError(uri, queryParams, responseBody));
        return null;
      }
    } else {
      log.error(buildError(uri, queryParams, result.getBody()));
      return null;
    }
  }

  public <T> T executeGet(String uri, Map<String, Object> queryParams, Class<T> clazz) {
    BaseProperties baseProperties = SpringApplicationContext.getBean(BaseProperties.class);
    String url = baseProperties.getBaseUrl() + uri;
    if (queryParams == null) {
      queryParams = new HashMap<>();
    }
    url = UrlUtils.build(url, queryParams);
    HttpUtils.HttpResult result = HttpUtils.get(url);
    if (result.isSuccess()) {
      String body = result.getBody();
      BaseResponse baseResponse = JsonUtils.fromJson(body, BaseResponse.class);
      if (baseResponse == null) {
        log.error(buildError(uri, queryParams, body));
        return null;
      }
      if (Objects.equals(baseResponse.getErrCode(), 0)) {
        log.debug(body);
        return JsonUtils.fromJson(body, clazz);
      } else {
        log.error(buildError(uri, queryParams, body));
        return null;
      }
    } else {
      log.error(buildError(uri, queryParams, result.getBody()));
      return null;
    }
  }

  private String buildError(String uri, Map<String, Object> params, String body) {
    return "request error \n>>>>>>\n" +
        "uri: " + uri + "\n" +
        "query: " + params + "\n" +
        "response: " + body + "\n" +
        "<<<<<<";
  }

}
