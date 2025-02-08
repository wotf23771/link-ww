package link.ww.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Data
@ConfigurationProperties(prefix = BaseConstant.CONFIG_PREFIX)
public class BaseProperties implements InitializingBean {

  /**
   * 企业微信接口baseUrl
   */
  private String baseUrl;

  private String corpId;

  private AgentType agentType;

  private String defaultAgent;

  private String contactsSyncAgent;

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("baseUrl:{}", baseUrl);
    log.debug("corpId:{}", corpId);
    log.debug("agentType:{}", agentType);
    log.debug("defaultAgent:{}", defaultAgent);
    log.debug("contactsSyncAgent:{}", contactsSyncAgent);
  }

}
