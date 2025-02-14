package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 客户标签变更事件处理器
 *
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[change_external_tag]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 *   <ChangeType><![CDATA[create]]></ChangeType>
 *   <Id><![CDATA[tagid1]]></Id>
 *   <TagType><![CDATA[tag]]></TagType>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class ChangeExternalTagProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "change_external_tag");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现客户标签变更逻辑
  }
} 