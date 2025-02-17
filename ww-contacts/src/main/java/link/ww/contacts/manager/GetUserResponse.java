package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.contacts.domain.ContactsUserDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetUserResponse extends ContactsUserDetail {

  /**
   * 返回码
   */
  @JsonProperty("errcode")
  private Integer errCode;

  /**
   * 对返回码的文本描述内容
   */
  @JsonProperty("errmsg")
  private String errMsg;

  public boolean isSuccess() {
    return this.errCode == 0;
  }

}
