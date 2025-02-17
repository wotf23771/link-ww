package link.ww.local.domain;

import link.ww.base.AuthChannel;
import link.ww.base.domain.UserInfo;
import lombok.Data;

/**
 * 私有化企微登录时获得的用户信息
 *
 * @author wangxiaolei
 * @since 2025/2/17 10:44
 */
@Data
public class LocalUserInfo implements UserInfo {

  /**
   * 成员UserID
   */
  private String userId;

  /**
   * 手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
   */
  private String deviceId;

  /**
   * 成员身份信息，2：超级管理员, 4:分级管理员，5：普通成员
   */
  private Integer userType;

  private AuthChannel authChannel;

  @Override
  public String getUserId() {
    return userId;
  }

}
