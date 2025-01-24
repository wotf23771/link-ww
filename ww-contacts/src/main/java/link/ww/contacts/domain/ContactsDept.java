package link.ww.contacts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContactsDept {

  private Integer id;

  private Integer parentId;

  private Integer order;

  private String name;

  @JsonProperty("department_leader")
  private List<String> leader;

}
