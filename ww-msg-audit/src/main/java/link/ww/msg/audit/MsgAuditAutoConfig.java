package link.ww.msg.audit;

import link.common.constant.LinkConstant;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@EnableConfigurationProperties(MsgAuditProperties.class)
@ComponentScan(value = LinkConstant.COMPONENT_PREFIX + "ww.msg.audit", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class MsgAuditAutoConfig {

}
