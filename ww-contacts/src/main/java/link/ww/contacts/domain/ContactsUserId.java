package link.ww.contacts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ContactsUserId {

  @JsonProperty("userid")
  private String id;

  @JsonProperty("department")
  private String deptId;

}
