package link.ww.contacts.service;

import link.ww.base.service.AccessTokenService;
import link.ww.contacts.domain.ContactsUser;
import link.ww.contacts.manager.ListSimpleUserResponse;
import link.ww.contacts.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 query
 *
 * @author wangxiaolei
 * @since 2025/1/24 17:55
 */
@Service
public class UserQueryService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private UserManager userManager;

  public List<ContactsUser> listUser(Integer deptId) {
    String token = accessTokenService.getAccessToken();
    ListSimpleUserResponse response = userManager.listSimple(token, deptId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

}
