package link.wecom.agent.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.wecom.base.domain.BaseResponse;
import lombok.Data;

@Data
public class AccessTokenResponse extends BaseResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
