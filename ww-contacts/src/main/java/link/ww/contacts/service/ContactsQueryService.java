package link.ww.contacts.service;

import link.ww.agent.service.AgentTokenService;
import link.ww.base.BaseProperties;
import link.ww.contacts.domain.ContactsDeptId;
import link.ww.contacts.manager.DeptManager;
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
public class ContactsQueryService {

  @Autowired
  private AgentTokenService agentTokenService;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private DeptManager deptManager;

  public void listDept() {
    String token = agentTokenService.getAgentToken(baseProperties.getContactsSyncAgent());
    deptManager.listDept(token, 10158);
  }

  public List<ContactsDeptId> listDeptId(Integer parentId) {
    String token = agentTokenService.getAgentToken(baseProperties.getContactsSyncAgent());
    ListDeptSimpleResponse response = deptManager.listDeptSimple(token, parentId);
    if (response != null) {
      return response.getData();
    } else {
      return null;
    }
  }

}
