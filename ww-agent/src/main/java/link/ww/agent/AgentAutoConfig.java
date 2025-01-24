package link.ww.agent;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@ComponentScan(value = "link.ww.agent", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class AgentAutoConfig {

}
