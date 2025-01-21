package link.wecom.msg.audit;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@EnableConfigurationProperties(MsgAuditProperties.class)
@ComponentScan(value = "link.wecom.msg.audit", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MsgAuditAutoConfig {

}
