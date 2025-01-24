package link.ww.contacts.service;

import link.ww.agent.service.AgentTokenService;
import link.ww.base.BaseProperties;
import link.ww.base.domain.BaseResponse;
import link.ww.contacts.manager.DeptManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通讯录 command
 *
 * @author wangxiaolei
 * @since 2025/1/24 14:30
 */
@Service
public class ContactsCmdService {

  @Autowired
  private AgentTokenService agentTokenService;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private DeptManager deptManager;

  public boolean deleteDept(Integer id) {
    String token = agentTokenService.getAgentToken(baseProperties.getContactsSyncAgent());
    BaseResponse result = deptManager.delete(token, id);
    if (result == null) {
      return false;
    }
    return result.isSuccess();
  }

}
