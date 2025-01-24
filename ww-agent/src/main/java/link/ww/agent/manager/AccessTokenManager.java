package link.ww.agent.manager;

import link.common.utils.HttpUtils;
import link.common.utils.JsonUtils;
import link.common.utils.StringUtils;
import link.common.utils.UrlUtils;
import link.ww.base.BaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class AccessTokenManager {

  @Autowired
  private BaseProperties baseProperties;

  public AccessTokenResponse getAccessToken(String corpId, String corpSecret) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(corpSecret)) {
      log.error("获取企业微信AccessToken失败，corpId:{}, secret:{}", corpId, corpSecret);
      return null;
    }
    String baseUrl = baseProperties.getBaseUrl();
    String url = baseUrl + "/gettoken";
    Map<String, String> params = new HashMap<>();
    params.put("corpid", corpId);
    params.put("corpsecret", corpSecret);
    url = UrlUtils.build(url, params);
    HttpUtils.HttpResult httpResult = HttpUtils.get(url);
    if (httpResult.isSuccess()) {
      String body = httpResult.getBody();
      AccessTokenResponse response = JsonUtils.fromJsonForce(body, AccessTokenResponse.class);
      if (response == null) {
        log.error("获取企业微信AccessToken失败，corpId:{}, corpSecret:{}", corpId, corpSecret);
      }
      if (!Objects.equals(response.getErrCode(), 0)) {
        log.error(response.getErrMsg());
      }
      return response;
    }
    return null;
  }

}
