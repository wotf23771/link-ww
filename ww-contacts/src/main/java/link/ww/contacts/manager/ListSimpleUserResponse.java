package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.domain.ContactsUser;
import lombok.Data;

import java.util.List;

@Data
public class ListSimpleUserResponse extends BaseResponse {

  @JsonProperty("userlist")
  private List<ContactsUser> data;

}
