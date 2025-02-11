package link.ww.base.event;

import link.common.utils.XmlUtils;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * 数据回调事件
 * 用于处理企业微信的数据回调请求
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Getter
public class DataCallbackEvent extends ApplicationEvent {

  /**
   * 消息类型
   */
  private String msgType;

  /**
   * 事件类型
   */
  private String event;

  /**
   * XML消息体
   */
  private String xmlBody;

  /**
   * XML根节点
   */
  private Element rootElement;

  public DataCallbackEvent(Object source, String xmlBody) {
    super(source);
    parseXmlBody(xmlBody);
  }

  private void parseXmlBody(String xmlBody) {
    try {
      this.xmlBody = xmlBody;
      this.rootElement = XmlUtils.getRootElement(xmlBody);
      this.msgType = XmlUtils.getFirstTagContent(rootElement, "MsgType");
      this.event = XmlUtils.getFirstTagContent(rootElement, "Event");
    } catch (SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Element getRootElement() {
    return rootElement;
  }

  public String getXmlBody() {
    return xmlBody;
  }
}
