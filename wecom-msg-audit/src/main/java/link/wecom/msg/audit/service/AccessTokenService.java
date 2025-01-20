package link.wecom.msg.audit.service;

import link.wecom.base.service.AgentTokenService;
import link.wecom.msg.audit.MsgAuditProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * access_token 服务
 *
 * @author wangxiaolei
 * @since 2024/12/3 21:23
 */
@Service
public class AccessTokenService {

  @Autowired
  private AgentTokenService agentTokenService;

  @Autowired
  private MsgAuditProperties msgAuditProperties;

  public String getAccessToken() {
    return agentTokenService.getAgentToken(msgAuditProperties.getAgent());
  }

}
