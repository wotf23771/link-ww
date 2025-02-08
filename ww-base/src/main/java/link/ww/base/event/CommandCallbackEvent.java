package link.ww.base.event;

import link.common.utils.XmlUtils;
import org.springframework.context.ApplicationEvent;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * 指令回调事件
 */
public class CommandCallbackEvent extends ApplicationEvent {

  private String infoType;

  private String xmlBody;

  private Element rootElement;

  public CommandCallbackEvent(Object source, String xmlBody) {
    super(source);
    parseXmlBody(xmlBody);
  }

  public String getInfoType() {
    return infoType;
  }

  public String getXmlBody() {
    return xmlBody;
  }

  public String getElement(String name) {
    return XmlUtils.getFirstTagContent(rootElement, name);
  }

  private void parseXmlBody(String xmlBody) {
    try {
      this.xmlBody = xmlBody;
      this.rootElement = XmlUtils.getRootElement(xmlBody);
      this.infoType = getElement("InfoType");
    } catch (SAXException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
