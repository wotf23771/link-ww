package link.ww.third.callback.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 变更授权通知处理器
 * 
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[change_auth]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Component
public class ChangeAuthProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "change_auth");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现变更授权逻辑
  }

}