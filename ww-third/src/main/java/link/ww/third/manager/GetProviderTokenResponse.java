package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

@Data
public class GetProviderTokenResponse extends BaseResponse {

  @JsonProperty("provider_access_token")
  private String providerAccessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
