package link.ww.third.callback.command;

import org.w3c.dom.Element;

public interface CommandProcessor {

  boolean support(String infoType);

  void process(Element root);

}
