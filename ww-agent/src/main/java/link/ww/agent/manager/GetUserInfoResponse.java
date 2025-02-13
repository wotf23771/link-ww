package link.ww.agent.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 获取访问用户身份
 *
 * @author wangxiaolei
 * @since 2025/2/13 16:34
 */
@Data
public class GetUserInfoResponse extends BaseResponse {

  @JsonProperty("userid")
  private String userId;

  @JsonProperty("user_ticket")
  private String userTicket;

}
