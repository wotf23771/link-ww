package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 应用版本变更通知处理器
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class ChangeAppVersionProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "change_app_version");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现应用版本变更逻辑
  }
} 