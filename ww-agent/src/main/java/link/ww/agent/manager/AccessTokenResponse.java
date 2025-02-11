package link.ww.agent.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 企业微信访问凭证响应
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Data
public class AccessTokenResponse extends BaseResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
