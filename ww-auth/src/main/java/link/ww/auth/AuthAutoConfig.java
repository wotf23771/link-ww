package link.ww.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@ComponentScan(value = "link.ww.auth", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class AuthAutoConfig {

}
