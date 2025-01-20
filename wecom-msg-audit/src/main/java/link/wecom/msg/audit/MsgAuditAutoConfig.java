package link.wecom.msg.audit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MsgAuditProperties.class)
@ComponentScan("link.wecom.msg.audit")
public class MsgAuditAutoConfig {

}
