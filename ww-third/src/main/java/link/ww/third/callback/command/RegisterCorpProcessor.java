package link.ww.third.callback.command;

import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 企业注册完成回调通知处理器
 * 
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <InfoType><![CDATA[register_corp]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <RegisterCode><![CDATA[GFVyYAQFVyYAQVyYAQVyYAQVyY]]></RegisterCode>
 *   <AuthCorpId><![CDATA[wxf8b4f85f3a794e77]]></AuthCorpId>
 *   <ContactSync>
 *     <AccessToken><![CDATA[accesstoken000001]]></AccessToken>
 *     <ExpiresIn>1800</ExpiresIn>
 *   </ContactSync>
 *   <AuthUserInfo>
 *     <UserId><![CDATA[zhangsan]]></UserId>
 *   </AuthUserInfo>
 *   <State><![CDATA[state]]></State>
 *   <TemplateId><![CDATA[tpl-123123123]]></TemplateId>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Component
public class RegisterCorpProcessor implements CommandProcessor {

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "register_corp");
  }

  @Override
  public void process(Element root) {
    // TODO: 实现企业注册完成逻辑
  }
} 