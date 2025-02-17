package link.ww.local.service;

import link.ww.base.domain.UserInfo;
import link.ww.base.service.AccessTokenService;
import link.ww.base.service.AuthUserInfoService;
import link.ww.local.domain.LocalUserInfo;
import link.ww.local.manager.AuthManager;
import link.ww.local.manager.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证后访问用户身份
 */
@Service
public class LocalAuthUserInfoService implements AuthUserInfoService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private AuthManager authManager;

  @Override
  public UserInfo getUserInfo(String code) {
    String token = accessTokenService.getAccessToken();
    GetUserInfoResponse response = authManager.getUserInfo(token, code);
    if (response != null) {
      LocalUserInfo userInfo = new LocalUserInfo();
      userInfo.setUserId(response.getUserId());
      userInfo.setDeviceId(response.getDeviceId());
      userInfo.setUserType(response.getUserType());
      return userInfo;
    }
    return null;
  }

}
