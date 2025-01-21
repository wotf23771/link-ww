package link.wecom.agent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component("agentProperties")
@ConfigurationProperties(prefix = "link.wecom.agent")
public class AgentProperties {

  private String callbackPrefix;

  private Map<String, Agent> agents;

}
