package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.domain.ContactsUserDetail;
import lombok.Data;

import java.util.List;

@Data
public class ListUserResponse extends BaseResponse {

  @JsonProperty("userlist")
  private List<ContactsUserDetail> data;

}
