package link.ww.base;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

/**
 * 企业微信基础模块自动配置
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Configuration
@EnableConfigurationProperties(BaseProperties.class)
@ComponentScan(value = BaseConstant.COMPONENT_SCOPE, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class BaseAutoConfig {

}
