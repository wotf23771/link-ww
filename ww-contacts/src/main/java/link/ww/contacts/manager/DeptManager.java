package link.ww.contacts.manager;

import link.ww.base.domain.BaseResponse;
import link.ww.base.manager.BaseManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeptManager extends BaseManager {

  public ListDeptResponse listDept(String accessToken, Integer id) {
    String uri = "/department/list";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return get(uri, params, ListDeptResponse.class);
  }

  public ListDeptSimpleResponse listDeptSimple(String accessToken, Integer id) {
    String uri = "/department/simplelist";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return get(uri, params, ListDeptSimpleResponse.class);
  }

  public BaseResponse delete(String accessToken, Integer id) {
    String uri = "/department/delete";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return get(uri, params, BaseResponse.class);
  }

}
