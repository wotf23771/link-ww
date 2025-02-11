package link.ww.agent.service;

import link.ww.base.BaseProperties;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.manager.DeptManager;
import link.ww.contacts.service.DeptCmdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通讯录 command
 *
 * @author wangxiaolei
 * @since 2025/1/24 14:30
 */
@Service
public class AgentDeptCmdService implements DeptCmdService {

  @Autowired
  private AgentTokenService agentTokenService;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private DeptManager deptManager;

  @Override
  public boolean deleteDept(Integer id) {
    String token = agentTokenService.getAgentToken(baseProperties.getContactsSyncAgent());
    BaseResponse result = deptManager.delete(token, id);
    if (result == null) {
      return false;
    }
    return result.isSuccess();
  }

}
