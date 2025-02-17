package link.ww.base.service;

import link.ww.base.domain.UserInfo;

/**
 * 用户认证后访问用户身份
 *
 * @author wangxiaolei
 * @since 2025/2/14 17:01
 */
public interface AuthUserInfoService {

  /**
   * 根据code获取成员信息
   *
   * @param code
   * @return
   */
  UserInfo getUserInfo(String code);

}
