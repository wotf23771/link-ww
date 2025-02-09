package link.ww.third.callback.command;

import link.ww.base.event.CommandCallbackEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandListener {

  @Autowired
  private ObjectProvider<CommandProcessor> processors;

  @Async
  @EventListener(CommandCallbackEvent.class)
  public void commandEventListener(CommandCallbackEvent event) {
    String infoType = event.getInfoType();
    processors.orderedStream().forEach(processor -> {
      if (processor.support(infoType)) {
        log.debug("command callback infoType: {}, xml: {}", infoType, event.getXmlBody());
        processor.process(event.getRootElement());
      }
    });
  }

}
