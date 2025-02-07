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

  /**
   * 获取部门列表
   *
   * @param accessToken 调用接口凭证
   * @param id          部门id。获取指定部门及其下的子部门（以及子部门的子部门等等，递归）。 如果不填，默认获取全量组织架构
   * @return
   */
  public ListDeptResponse list(String accessToken, Integer id) {
    String uri = "/department/list";
    Map<String, Object> params = new HashMap<>();
    if (id != null) {
      params.put("id", id);
    }
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListDeptResponse.class);
  }

  /**
   * 获取子部门ID列表
   *
   * @param accessToken 调用接口凭证
   * @param id          部门id。获取指定部门及其下的子部门（以及子部门的子部门等等，递归）。 如果不填，默认获取全量组织架构
   * @return
   */
  public ListDeptSimpleResponse listSimple(String accessToken, Integer id) {
    String uri = "/department/simplelist";
    Map<String, Object> params = new HashMap<>();
    if (id != null) {
      params.put("id", id);
    }
    params.put("access_token", accessToken);
    return executeGet(uri, params, ListDeptSimpleResponse.class);
  }

  /**
   * 删除部门
   *
   * @param accessToken 调用接口凭证
   * @param id          部门id。（注：不能删除根部门；不能删除含有子部门、成员的部门）
   * @return
   */
  public BaseResponse delete(String accessToken, Integer id) {
    Assert.notNull(id, "部门id不能为空");
    String uri = "/department/delete";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return executeGet(uri, params, BaseResponse.class);
  }

  /**
   * 获取单个部门详情
   *
   * @param accessToken 调用接口凭证
   * @param id          部门id
   * @return
   */
  public GetDeptResponse get(String accessToken, Integer id) {
    Assert.notNull(id, "部门id不能为空");
    String uri = "/department/get";
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    params.put("access_token", accessToken);
    return executeGet(uri, params, GetDeptResponse.class);
  }

}
