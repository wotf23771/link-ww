package link.ww.base.event;

import link.common.utils.XmlUtils;
import org.springframework.context.ApplicationEvent;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * 企业微信指令回调事件
 * 用于处理企业微信的指令回调消息
 *
 * @author wangxiaolei
 * @since 2025/2/10
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

  public Element getRootElement() {
    return rootElement;
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
