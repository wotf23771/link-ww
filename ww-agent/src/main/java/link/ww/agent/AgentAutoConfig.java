package link.ww.agent;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

/**
 * 企业微信应用模块自动配置
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Configuration
@ComponentScan(value = AgentConstant.COMPONENT_SCOPE, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class AgentAutoConfig {

}
