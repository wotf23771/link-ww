package link.ww.base;

import link.common.constant.LinkConstant;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@EnableConfigurationProperties(BaseProperties.class)
@ComponentScan(value = LinkConstant.COMPONENT_PREFIX + ".ww.base", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class BaseAutoConfig {

}
