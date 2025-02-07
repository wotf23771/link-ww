package link.ww.contacts.manager;

import link.ww.base.manager.BaseManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserManager extends BaseManager {

  public ListSimpleUserResponse listSimple(String accessToken, Integer deptId) {
    Assert.notNull(deptId, "部门id不能为空");
    String uri = "/user/simplelist";
    Map<String, Object> params = new HashMap<>();
    params.put("department_id", deptId);
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListSimpleUserResponse.class);
  }

  public ListUserResponse list(String accessToken, Integer deptId) {
    Assert.notNull(deptId, "部门id不能为空");
    String uri = "/user/list";
    Map<String, Object> params = new HashMap<>();
    params.put("department_id", deptId);
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListUserResponse.class);
  }

  public GetUserResponse get(String accessToken, String userId) {
    Assert.notNull(userId, "用户id不能为空");
    String uri = "/user/get";
    Map<String, Object> params = new HashMap<>();
    params.put("userid", userId);
    params.put("access_token", accessToken);
    return executeGet(uri, params, GetUserResponse.class);
  }

}
