package link.ww.third.manager;

import link.common.utils.JsonUtils;
import link.ww.base.manager.BaseManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceManager extends BaseManager {

  /**
   * 获取服务商凭证
   *
   * @param corpId
   * @param providerSecret
   * @return
   */
  public GetProviderTokenResponse getProviderToken(String corpId, String providerSecret) {
    Assert.notNull(corpId, "服务商的corpid不能为空");
    Assert.notNull(providerSecret, "服务商的secret不能为空");
    String uri = "/service/get_provider_token";
    Map<String, Object> params = new HashMap<>();
    params.put("corpid", corpId);
    params.put("provider_secret", providerSecret);
    return executePost(uri, null, JsonUtils.toJson(params), GetProviderTokenResponse.class);
  }

  /**
   * 获取第三方应用凭证
   *
   * @param suite_id
   * @param suite_secret
   * @param suite_ticket
   * @return
   */
  public GetSuiteTokenResponse getSuiteToken(String suite_id, String suite_secret, String suite_ticket) {
    Assert.notNull(suite_id, "第三方应用id或者代开发应用模板id不能为空");
    Assert.notNull(suite_secret, "第三方应用secret 或者代开发应用模板secret不能为空");
    Assert.notNull(suite_ticket, "企业微信后台推送的ticket不能为空");
    String uri = "/service/get_suite_token";
    Map<String, Object> params = new HashMap<>();
    params.put("suite_id", suite_id);
    params.put("suite_secret", suite_secret);
    params.put("suite_ticket", suite_ticket);
    return executePost(uri, null, JsonUtils.toJson(params), GetSuiteTokenResponse.class);
  }

  /**
   * 获取预授权码
   *
   * @param suiteAccessToken
   * @return
   */
  public GetPreAuthCodeResponse getPreAuthCode(String suiteAccessToken) {
    Assert.notNull(suiteAccessToken, "SuiteAccessToken不能为空");
    String uri = "/service/get_pre_auth_code";
    Map<String, Object> params = new HashMap<>();
    params.put("suite_access_token", suiteAccessToken);
    return executeGet(uri, params, GetPreAuthCodeResponse.class);
  }

  /**
   * 获取企业永久授权码
   *
   * @param suiteAccessToken
   * @param authCode         临时授权码
   * @return
   */
  public GetPermanentCodeResponse getPermanentCode(String suiteAccessToken, String authCode) {
    Assert.notNull(suiteAccessToken, "SuiteAccessToken不能为空");
    Assert.notNull(authCode, "临时授权码不能为空");
    String uri = "/service/v2/get_permanent_code";
    Map<String, Object> queryParam = new HashMap<>();
    queryParam.put("suite_access_token", suiteAccessToken);

    Map<String, String> bodyParam = new HashMap<>();
    bodyParam.put("auth_code", authCode);

    return executePost(uri, queryParam, JsonUtils.toJson(bodyParam), GetPermanentCodeResponse.class);
  }

}
