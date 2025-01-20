package link.wecom.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "link.wecom.base")
public class BaseProperties {

  /**
   * 企业微信接口baseUrl
   */
  private String baseUrl;

  private String corpId;

  private Map<String, String> agents;

  private String defaultAgent;

}
