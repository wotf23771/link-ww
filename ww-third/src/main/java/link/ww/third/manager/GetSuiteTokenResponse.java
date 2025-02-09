package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

@Data
public class GetSuiteTokenResponse extends BaseResponse {

  @JsonProperty("suite_access_token")
  private String suiteAccessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
