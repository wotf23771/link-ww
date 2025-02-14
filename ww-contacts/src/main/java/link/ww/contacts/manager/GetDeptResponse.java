package link.ww.contacts.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.domain.ContactsDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class GetDeptResponse extends BaseResponse {

  @JsonProperty("department")
  private ContactsDept data;

}
