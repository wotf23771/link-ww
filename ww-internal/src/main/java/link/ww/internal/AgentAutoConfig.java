package link.ww.internal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(name = "link.ww.base.app-type", havingValue = "internal")
@ComponentScan(value = AgentConstant.COMPONENT_SCOPE, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class AgentAutoConfig {

}
