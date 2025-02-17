package link.ww.internal.domain;

import link.ww.base.AuthChannel;
import link.ww.base.domain.UserInfo;
import lombok.Data;

/**
 * saas内部应用登录时获得的用户信息
 *
 * @author wangxiaolei
 * @since 2025/2/17 14:27
 */
@Data
public class AgentUserInfo implements UserInfo {

  private String userId;

  private String userTicket;

  private AuthChannel authChannel;

  @Override
  public String getUserId() {
    return userId;
  }

}
