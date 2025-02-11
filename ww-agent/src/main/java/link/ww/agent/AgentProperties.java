package link.ww.agent;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 企业微信应用配置属性
 * 用于配置企业微信应用的回调地址和应用信息
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Data
@Component("agentProperties")
@ConfigurationProperties(prefix = AgentConstant.CONFIG_PREFIX)
public class AgentProperties implements InitializingBean {

  /**
   * 回调接口前缀
   * 用于接收企业微信的回调请求
   */
  private String callbackPrefix;

  /**
   * 应用配置映射
   * key: 应用标识
   * value: 应用配置信息
   */
  private Map<String, Agent> agents;

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("callbackPrefix:{}", callbackPrefix);
    log.debug("agents:{}", agents);
  }

}
