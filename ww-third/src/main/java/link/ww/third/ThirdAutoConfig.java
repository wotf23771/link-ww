package link.ww.third;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@Configuration
@ConditionalOnProperty(name = "link.ww.base.app-type", havingValue = "third")
@MapperScan(basePackages = ThirdConstant.COMPONENT_SCOPE, markerInterface = BaseMapper.class)
@ComponentScan(value = ThirdConstant.COMPONENT_SCOPE, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class ThirdAutoConfig {

}
