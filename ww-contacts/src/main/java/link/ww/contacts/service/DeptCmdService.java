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
public class DeptCmdService {

  @Autowired
  private AgentTokenService agentTokenService;

  @Autowired
  private BaseProperties baseProperties;

  @Autowired
  private DeptManager deptManager;

  /**
   * 删除部门
   *
   * @param id 部门id。（注：不能删除根部门；不能删除含有子部门、成员的部门）
   * @return
   */
  public boolean deleteDept(Integer id) {
    String token = agentTokenService.getAgentToken(baseProperties.getContactsSyncAgent());
    BaseResponse result = deptManager.delete(token, id);
    if (result == null) {
      return false;
    }
    return result.isSuccess();
  }

}
