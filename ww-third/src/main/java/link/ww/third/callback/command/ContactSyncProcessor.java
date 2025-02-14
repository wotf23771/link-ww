package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 通讯录变更事件回调处理器
 *
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[contact_sync]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 *   <ChangeType><![CDATA[sync_user]]></ChangeType>
 *   <UserID><![CDATA[zhangsan]]></UserID>
 *   <Department><![CDATA[1,2,3]]></Department>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class ContactSyncProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "contact_sync");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现通讯录同步逻辑
  }
} 