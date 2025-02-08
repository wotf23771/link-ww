package link.ww.agent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Data
@Component("agentProperties")
@ConfigurationProperties(prefix = AgentConstant.CONFIG_PREFIX)
public class AgentProperties implements InitializingBean {

  private String callbackPrefix;

  private Map<String, Agent> agents;

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("callbackPrefix:{}", callbackPrefix);
    log.debug("agents:{}", agents);
  }

}
