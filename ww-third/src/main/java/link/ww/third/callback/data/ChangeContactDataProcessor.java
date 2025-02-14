package link.ww.third.callback.data;

import link.ww.base.event.DataCallbackEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 通讯录变更数据回调处理器
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Component
public class ChangeContactDataProcessor implements DataProcessor {

  @Override
  public boolean support(String msgType, String event) {
    return Objects.equals(msgType, "event") 
        && Objects.equals(event, "change_contact");
  }

  @Override
  public void process(DataCallbackEvent event) {
    log.debug("收到通讯录变更通知");
    Element root = event.getRootElement();
    // TODO: 实现通讯录变更逻辑
  }
} 