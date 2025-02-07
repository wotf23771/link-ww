package link.ww.contacts.service;

import link.ww.base.service.AccessTokenService;
import link.ww.contacts.domain.ContactsUser;
import link.ww.contacts.domain.ContactsUserDetail;
import link.ww.contacts.manager.ListSimpleUserResponse;
import link.ww.contacts.manager.ListUserResponse;
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

  /**
   * 获取部门成员基本信息
   *
   * @param deptId 部门ID
   * @return
   */
  public List<ContactsUser> listUser(Integer deptId) {
    String token = accessTokenService.getAccessToken();
    ListSimpleUserResponse response = userManager.listSimple(token, deptId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

  /**
   * 获取部门成员详细信息
   *
   * @param deptId 部门ID
   * @return
   */
  public List<ContactsUserDetail> listUserDetail(Integer deptId) {
    String token = accessTokenService.getAccessToken();
    ListUserResponse response = userManager.list(token, deptId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

  /**
   * 获取成员详细信息
   *
   * @param userId 成员userId
   * @return
   */
  public ContactsUserDetail getUserDetail(String userId) {
    String token = accessTokenService.getAccessToken();
    return userManager.get(token, userId);
  }

}
