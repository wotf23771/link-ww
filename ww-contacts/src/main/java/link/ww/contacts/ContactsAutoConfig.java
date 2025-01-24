package link.ww.contacts;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@ComponentScan(value = "link.ww.contacts", nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class ContactsAutoConfig {

}
