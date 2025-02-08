package link.ww.base;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@EnableConfigurationProperties(BaseProperties.class)
@ComponentScan(value = BaseConstant.COMPONENT_SCOPE, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class BaseAutoConfig {

}
