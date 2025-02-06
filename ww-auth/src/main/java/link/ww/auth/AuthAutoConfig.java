package link.ww.auth;

import link.common.constant.LinkConstant;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@ComponentScan(value = LinkConstant.COMPONENT_PREFIX + "ww.auth", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class AuthAutoConfig {

}
