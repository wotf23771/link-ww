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

}
