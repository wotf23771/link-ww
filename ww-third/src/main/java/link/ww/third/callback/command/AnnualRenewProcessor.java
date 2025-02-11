package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 企业年审通知处理器
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class AnnualRenewProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "annual_renew");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现企业年审逻辑
  }
} 