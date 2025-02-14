package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 取消授权通知处理器
 * 
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[cancel_auth]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class CancelAuthProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "cancel_auth");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现取消授权逻辑
  }

}