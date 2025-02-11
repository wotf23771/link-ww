package link.ww.third.callback.command;

import link.common.utils.XmlUtils;
import link.ww.third.domain.entity.ThirdCorp;
import link.ww.third.manager.GetPermanentCodeResponse;
import link.ww.third.manager.ServiceManager;
import link.ww.third.service.SuiteTokenService;
import link.ww.third.service.ThirdCorpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 应用授权通知处理器
 *
 * <pre>
 * XML格式:
 * {@code
 * <xml>
 *   <SuiteId><![CDATA[ww4asffe99e54c0f4c]]></SuiteId>
 *   <AuthCode><![CDATA[AUTHCODE]]></AuthCode>
 *   <InfoType><![CDATA[create_auth]]></InfoType>
 *   <TimeStamp>1403610513</TimeStamp>
 *   <State><![CDATA[state]]></State>
 * </xml>
 * }
 * </pre>
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Component
public class CreateAuthProcessor implements CommandProcessor {

  @Autowired
  private SuiteTokenService suiteTokenService;

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private ThirdCorpService thirdCorpService;

  @Override
  public boolean support(String infoType) {
    return Objects.equals(infoType, "create_auth");
  }

  @Override
  public void process(Element root) {
    String authCode = XmlUtils.getFirstTagContent(root, "AuthCode");
    String suiteAccessToken = suiteTokenService.getToken();

    GetPermanentCodeResponse response = serviceManager.getPermanentCode(suiteAccessToken, authCode);
    if (response == null) {
      log.error("Failed to get permanent code for authCode: {}", authCode);
      return;
    }

    saveOrUpdateThirdCorp(response);

    // 更新授权信息，即更新应用、成员信息
    thirdCorpService.updateAuthInfo(response.getAuthCorpInfo().getCorpId());
  }

  private void saveOrUpdateThirdCorp(GetPermanentCodeResponse response) {
    String corpId = response.getAuthCorpInfo().getCorpId();
    String corpName = response.getAuthCorpInfo().getCorpName();
    String permanentCode = response.getPermanentCode();

    ThirdCorp thirdCorp = thirdCorpService.get(corpId);
    if (thirdCorp == null) {
      thirdCorp = new ThirdCorp();
      thirdCorp.setId(corpId);
      thirdCorp.setName(corpName);
      thirdCorp.setPermanentCode(permanentCode);
      thirdCorpService.save(thirdCorp);
      log.info("Created new ThirdCorp for corpId: {}", corpId);
    } else {
      thirdCorp.setName(corpName);
      thirdCorp.setPermanentCode(permanentCode);
      thirdCorpService.update(thirdCorp);
      log.info("Updated existing ThirdCorp for corpId: {}", corpId);
    }
  }

}
