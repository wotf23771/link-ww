package link.ww.contacts.manager;

import link.ww.base.domain.BaseResponse;
import link.ww.base.manager.BaseManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeptManager extends BaseManager {

  public ListDeptResponse list(String accessToken, Integer id) {
    String uri = "/department/list";
    Map<String, Object> params = new HashMap<>();
    if (id != null) {
      params.put("id", id);
    }
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListDeptResponse.class);
  }

  public ListDeptSimpleResponse listSimple(String accessToken, Integer id) {
    String uri = "/department/simplelist";
    Map<String, Object> params = new HashMap<>();
    if (id != null) {
      params.put("id", id);
    }
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListDeptSimpleResponse.class);
  }

  public BaseResponse delete(String accessToken, Integer id) {
    Assert.notNull(id, "部门id不能为空");
    String uri = "/department/delete";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return executeGet(uri, params, BaseResponse.class);
  }

  public GetDeptResponse get(String accessToken, Integer id) {
    Assert.notNull(id, "部门id不能为空");
    String uri = "/department/get";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return executeGet(uri, params, GetDeptResponse.class);
  }

}
