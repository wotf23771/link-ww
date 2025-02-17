package link.ww.internal.service;

import link.ww.base.domain.UserInfo;
import link.ww.base.service.AccessTokenService;
import link.ww.base.service.AuthUserInfoService;
import link.ww.internal.domain.AgentUserInfo;
import link.ww.internal.manager.AuthManager;
import link.ww.internal.manager.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证后访问用户身份
 */
@Service
public class AgentUserInfoService implements AuthUserInfoService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private AuthManager authManager;

  @Override
  public UserInfo getUserInfo(String code) {
    String token = accessTokenService.getAccessToken();
    GetUserInfoResponse response = authManager.getUserInfo(token, code);
    if (response != null) {
      AgentUserInfo userInfo = new AgentUserInfo();
      userInfo.setUserId(response.getUserId());
      userInfo.setUserTicket(response.getUserTicket());
      return userInfo;
    }
    return null;
  }

}
