package link.ww.third.manager;

import link.common.utils.JsonUtils;
import link.ww.base.manager.BaseManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 企业微信第三方应用服务接口管理
 * 主要用于处理企业微信开放平台的各种凭证获取和授权操作
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Service
public class ServiceManager extends BaseManager {

  /**
   * 获取服务商凭证
   * 该接口用于获取服务商的provider_access_token，服务商在获取企业微信开放平台开发的资源时，需要先获取provider_access_token
   * 详见：https://developer.work.weixin.qq.com/document/path/91200
   *
   * @param corpId 服务商的corpid
   * @param providerSecret 服务商的secret，在企业微信服务商管理后台查看
   * @return {@link GetProviderTokenResponse} 包含provider_access_token和有效期
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

  /**
   * 获取企业授权信息
   *
   * @param suiteAccessToken
   * @param authCorpId
   * @param permanentCode
   * @return
   */
  public GetAuthInfoResponse getAuthInfo(String suiteAccessToken, String authCorpId, String permanentCode) {
    Assert.notNull(suiteAccessToken, "SuiteAccessToken不能为空");
    Assert.notNull(authCorpId, "授权方corpid不能为空");
    Assert.notNull(permanentCode, "永久授权码不能为空");

    String uri = "/service/v2/get_auth_info";
    Map<String, Object> queryParam = new HashMap<>();
    queryParam.put("suite_access_token", suiteAccessToken);

    Map<String, String> bodyParam = new HashMap<>();
    bodyParam.put("auth_corpid", authCorpId);
    bodyParam.put("permanent_code", permanentCode);

    return executePost(uri, queryParam, JsonUtils.toJson(bodyParam), GetAuthInfoResponse.class);
  }

  /**
   * 获取企业凭证
   *
   * @param suiteAccessToken
   * @param authCorpId
   * @param permanentCode
   * @return
   */
  public GetCorpTokenResponse getCorpToken(String suiteAccessToken, String authCorpId, String permanentCode) {
    Assert.notNull(suiteAccessToken, "SuiteAccessToken不能为空");
    Assert.notNull(authCorpId, "授权方corpid不能为空");
    Assert.notNull(permanentCode, "永久授权码不能为空");

    String uri = "/service/get_corp_token";
    Map<String, Object> queryParam = new HashMap<>();
    queryParam.put("suite_access_token", suiteAccessToken);

    Map<String, String> bodyParam = new HashMap<>();
    bodyParam.put("auth_corpid", authCorpId);
    bodyParam.put("permanent_code", permanentCode);

    return executePost(uri, queryParam, JsonUtils.toJson(bodyParam), GetCorpTokenResponse.class);
  }

}
