package link.ww.base.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业的jsapi_ticket
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetCorpTicketResponse extends BaseResponse {

  public String ticket;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
