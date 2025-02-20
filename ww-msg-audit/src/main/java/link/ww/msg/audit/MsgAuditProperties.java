package link.ww.msg.audit;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = MsgAuditConstant.CONFIG_PREFIX)
public class MsgAuditProperties {

  private String agent;

  private EventSetting eventSetting;

  @Data
  public static class EventSetting {

    private String token;

    private String encodingAesKey;

  }

}
