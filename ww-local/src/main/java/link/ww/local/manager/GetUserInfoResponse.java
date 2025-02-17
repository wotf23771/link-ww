package link.ww.local.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取访问用户身份
 *
 * @author wangxiaolei
 * @since 2025/2/13 16:34
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetUserInfoResponse extends BaseResponse {

  /**
   * 成员UserID
   */
  @JsonProperty("UserId")
  private String userId;

  /**
   * 手机设备号(由企业微信在安装时随机生成，删除重装会改变，升级不受影响)
   */
  @JsonProperty("DeviceId")
  private String deviceId;

  /**
   * 成员身份信息，2：超级管理员, 4:分级管理员，5：普通成员
   */
  @JsonProperty("usertype")
  private Integer userType;

}
