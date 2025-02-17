package link.ww.base.domain;

import link.ww.base.AuthChannel;

/**
 * 认证后得到的用户信息
 *
 * @author wangxiaolei
 * @since 2025/2/14 17:04
 */
public interface UserInfo {

  String getUserId();

  AuthChannel getAuthChannel();

}
