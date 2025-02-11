package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 外部联系人变更事件处理器
 *
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[change_external_contact]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 *   <ChangeType><![CDATA[add_external_contact]]></ChangeType>
 *   <UserID><![CDATA[zhangsan]]></UserID>
 *   <ExternalUserID><![CDATA[woAJ2GCAAAXtWyujaWJHDDGi0mACH6AAA]]></ExternalUserID>
 *   <State><![CDATA[teststate]]></State>
 *   <WelcomeCode><![CDATA[WELCOMECODE]]></WelcomeCode>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class ChangeExternalContactProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "change_external_contact");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现外部联系人变更逻辑
  }
} 