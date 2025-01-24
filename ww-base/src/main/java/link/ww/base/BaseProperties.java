package link.ww.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "link.ww.base")
public class BaseProperties {

  /**
   * 企业微信接口baseUrl
   */
  private String baseUrl;

  private String corpId;

  private String defaultAgent;

}
