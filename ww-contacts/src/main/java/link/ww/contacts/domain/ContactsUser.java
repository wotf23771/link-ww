package link.ww.contacts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContactsUser {

  @JsonProperty("userid")
  private String id;

  private String name;

  @JsonProperty("department")
  private List<Integer> dept;

  @JsonProperty("open_userid")
  private String openUserId;

}
