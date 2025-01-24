package link.ww.base.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

@Slf4j
public class CallbackEvent extends ApplicationEvent {

  private String fromUserName;

  private String msgType;

  private String event;

  private String xmlBody;

  public CallbackEvent(Object source, String xmlBody) {
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

  private void parseXmlBody(String xmlBody) {
    DocumentBuilder db = getDocumentBuilder();
    StringReader sr = new StringReader(xmlBody);
    InputSource is = new InputSource(sr);
    try {
      this.xmlBody = xmlBody;
      Document document = db.parse(is);
      Element root = document.getDocumentElement();
      String fromUserName = getElementContent(root, "FromUserName");
      String msgType = getElementContent(root, "MsgType");
      String event = getElementContent(root, "Event");
      this.fromUserName = fromUserName;
      this.msgType = msgType;
      this.event = event;
    } catch (SAXException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private String getElementContent(Element root, String elementName) {
    if (root == null) {
      return null;
    }
    NodeList infoTypeList = root.getElementsByTagName(elementName);
    if (infoTypeList.getLength() > 0) {
      return infoTypeList.item(0).getTextContent();
    }
    return null;
  }

  private String getElementContent(String xml, String elementName) {
    try {
      DocumentBuilder db = getDocumentBuilder();
      StringReader sr = new StringReader(xml);
      InputSource is = new InputSource(sr);
      Document document = db.parse(is);
      Element root = document.getDocumentElement();
      return getElementContent(root, elementName);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

  private DocumentBuilder getDocumentBuilder() {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      return dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

}
