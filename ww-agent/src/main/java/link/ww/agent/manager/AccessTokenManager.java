package link.ww.agent.manager;

import link.common.utils.StringUtils;
import link.ww.base.manager.BaseManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class AccessTokenManager extends BaseManager {

  public AccessTokenResponse getAccessToken(String corpId, String corpSecret) {
    if (StringUtils.isBlank(corpId) || StringUtils.isBlank(corpSecret)) {
      log.error("获取企业微信AccessToken失败，corpId:{}, secret:{}", corpId, corpSecret);
      return null;
    }
    String uri = "/gettoken";
    Map<String, Object> params = new HashMap<>();
    params.put("corpid", corpId);
    params.put("corpsecret", corpSecret);
    return executeGet(uri, params, AccessTokenResponse.class);
  }

}
