package link.ww.contacts.service;

import link.ww.base.service.AccessTokenService;
import link.ww.contacts.domain.ContactsDept;
import link.ww.contacts.domain.ContactsDeptId;
import link.ww.contacts.manager.DeptManager;
import link.ww.contacts.manager.GetDeptResponse;
import link.ww.contacts.manager.ListDeptResponse;
import link.ww.contacts.manager.ListDeptSimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 通讯录 query
 *
 * @author wangxiaolei
 * @since 2025/1/24 14:30
 */
@Service
public class DeptQueryService {

  @Autowired
  private AccessTokenService accessTokenService;

  @Autowired
  private DeptManager deptManager;

  public List<ContactsDept> listDept(Integer parentId) {
    String token = accessTokenService.getAccessToken();
    ListDeptResponse response = deptManager.list(token, parentId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

  public List<ContactsDeptId> listDeptId(Integer parentId) {
    String token = accessTokenService.getAccessToken();
    ListDeptSimpleResponse response = deptManager.listSimple(token, parentId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

  public ContactsDept getDept(Integer deptId) {
    String token = accessTokenService.getAccessToken();
    GetDeptResponse response = deptManager.get(token, deptId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

}
