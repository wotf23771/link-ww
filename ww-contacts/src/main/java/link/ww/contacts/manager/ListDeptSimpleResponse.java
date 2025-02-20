package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.domain.ContactsDeptId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListDeptSimpleResponse extends BaseResponse {

  @JsonProperty("department_id")
  private List<ContactsDeptId> data;

}
