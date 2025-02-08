package link.ww.base.event;

import link.common.utils.XmlUtils;
import org.springframework.context.ApplicationEvent;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * 事件回调事件
 */
public class EventCallbackEvent extends ApplicationEvent {

  private String fromUserName;

  private String msgType;

  private String event;

  private String xmlBody;

  private Element rootElement;

  public EventCallbackEvent(Object source, String xmlBody) {
    super(source);
    parseXmlBody(xmlBody);
  }

  public String getFromUserName() {
    return fromUserName;
  }

  public String getMsgType() {
    return msgType;
  }

  public String getEvent() {
    return event;
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
      String fromUserName = getElement("FromUserName");
      String msgType = getElement("MsgType");
      String event = getElement("Event");
      this.fromUserName = fromUserName;
      this.msgType = msgType;
      this.event = event;
    } catch (SAXException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
