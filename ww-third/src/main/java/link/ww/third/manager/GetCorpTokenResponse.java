package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 获取企业凭证 响应
 *
 * @author wangxiaolei
 * @since 2025/2/10 18:54
 */
@Data
public class GetCorpTokenResponse extends BaseResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
