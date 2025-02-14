package link.ww.base.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 应用的jsapi_ticket
 *
 * @author wangxiaolei
 * @since 2025/2/14 11:27
 */
@Data
public class GetAgentTicketResponse extends BaseResponse {

  public String ticket;

  @JsonProperty("expires_in")
  private Long expiresIn;

}
