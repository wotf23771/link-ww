package link.ww.agent.manager;

import link.ww.base.manager.BaseManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证相关客户端
 *
 * @author wangxiaolei
 * @since 2025/2/13 16:28
 */
@Slf4j
@Service
public class AuthManager extends BaseManager {

  public GetUserInfoResponse getUserInfo(String accessToken, String code) {
    Assert.notNull(accessToken, "调用接口凭证不能为空");
    Assert.notNull(code, "授权获取到的code不能为空");
    String uri = "/auth/getuserinfo";
    Map<String, Object> params = new HashMap<>();
    params.put("access_token", accessToken);
    params.put("code", code);
    return executeGet(uri, params, GetUserInfoResponse.class);
  }

}
