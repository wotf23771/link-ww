package link.ww.third.manager;

import link.common.utils.JsonUtils;
import link.ww.base.manager.BaseManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceManager extends BaseManager {

  public GetProviderTokenResponse getProviderToken(String corpId, String providerSecret) {
    Assert.notNull(corpId, "服务商的corpid不能为空");
    Assert.notNull(providerSecret, "服务商的secret不能为空");
    String uri = "/service/get_provider_token";
    Map<String, Object> params = new HashMap<>();
    params.put("corpid", corpId);
    params.put("provider_secret", providerSecret);
    return executePost(uri, null, JsonUtils.toJson(params), GetProviderTokenResponse.class);
  }

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

}
