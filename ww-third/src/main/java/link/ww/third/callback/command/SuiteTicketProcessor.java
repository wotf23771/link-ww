package link.ww.third.callback.command;

import link.common.utils.XmlUtils;
import link.ww.third.service.SuiteTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 刷新Ticket
 * 
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *     <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *     <InfoType><![CDATA[suite_ticket]]></InfoType>
 *     <TimeStamp>1403610513</TimeStamp>
 *     <SuiteTicket><![CDATA[asdfasfas]]></SuiteTicket>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class SuiteTicketProcessor implements CommandProcessor {

  @Autowired
  private SuiteTicketService suiteTicketService;

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "suite_ticket");
  }

  @Override
  public void process(Element root) {
    String suiteTicket = XmlUtils.getFirstTagContent(root, "SuiteTicket");
    suiteTicketService.setTicket(suiteTicket);
  }

}
