package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 获取预授权码 响应
 *
 * @author wangxiaolei
 * @since 2025/2/10 10:06
 */
@Data
public class GetPreAuthCodeResponse extends BaseResponse {

  @JsonProperty("pre_auth_code")
  private String preAuthCode;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
