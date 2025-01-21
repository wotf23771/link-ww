package link.wecom.agent.service;

import link.common.utils.MapUtils;
import link.wecom.agent.Agent;
import link.wecom.agent.AgentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class AgentService {

  @Autowired
  private AgentProperties agentProperties;

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
