package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.domain.ContactsDept;
import lombok.Data;

import java.util.List;

@Data
public class ListDeptResponse extends BaseResponse {

  @JsonProperty("department")
  private List<ContactsDept> data;

}
