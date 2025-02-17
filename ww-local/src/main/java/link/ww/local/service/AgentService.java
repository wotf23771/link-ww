package link.ww.local.service;

import link.common.utils.MapUtils;
import link.ww.local.Agent;
import link.ww.local.LocalProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * 企业微信应用服务
 * 提供应用配置的查询和管理功能
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Service
public class AgentService {

  @Autowired
  private LocalProperties agentProperties;

  public Agent getAgentByName(String agentName) {
    Map<String, Agent> agents = agentProperties.getAgents();
    if (MapUtils.isEmpty(agents)) {
      return null;
    }
    return agents.get(agentName);
  }

  public Agent getAgentById(String agentId) {
    Map<String, Agent> agents = agentProperties.getAgents();
    if (MapUtils.isEmpty(agents)) {
      return null;
    }
    for (Agent agent : agents.values()) {
      if (Objects.equals(agent.getAgentId(), agentId)) {
        return agent;
      }
    }
    return null;
  }

}
