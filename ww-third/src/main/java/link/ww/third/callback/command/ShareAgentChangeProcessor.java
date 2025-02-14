package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 共享应用事件变更通知处理器
 * 
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[share_agent_change]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 *   <AgentId>1</AgentId>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class ShareAgentChangeProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "share_agent_change");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现共享应用变更逻辑
  }

}