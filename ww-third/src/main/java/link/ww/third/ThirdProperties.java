package link.ww.third;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component("thirdProperties")
@ConfigurationProperties(prefix = ThirdConstant.CONFIG_PREFIX)
public class ThirdProperties {

  private Common common;

  private String suiteId;

  private String secret;

  private String token;

  private String encodingAesKey;

  private String callbackPrefix;

  @Data
  public static class Common {

    private String providerSecret;

    private String token;

    private String encodingAesKey;

  }

}
