package link.ww.agent.service;

import link.ww.agent.manager.AuthManager;
import link.ww.agent.manager.GetUserInfoResponse;
import link.ww.base.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证后访问用户身份
 */
@Service
public class AuthUserInfoService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private AuthManager authManager;

  public String getUserId(String code) {
    String token = accessTokenService.getAccessToken();
    GetUserInfoResponse response = authManager.getUserInfo(token, code);
    if (response != null) {
      return response.getUserId();
    }
    return null;
  }

}
