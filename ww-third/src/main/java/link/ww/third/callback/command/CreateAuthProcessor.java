package link.ww.third.callback.command;

import link.common.utils.XmlUtils;
import link.ww.third.domain.entity.ThirdCorp;
import link.ww.third.manager.GetPermanentCodeResponse;
import link.ww.third.manager.ServiceManager;
import link.ww.third.service.SuiteTokenService;
import link.ww.third.service.ThirdCorpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;

import java.util.Objects;

/**
 * 应用授权
 *
 * @author wangxiaolei
 * @since 2025/2/10 10:26
 */
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
      return;
    }
    String permanentCode = response.getPermanentCode();
    String corpId = response.getAuthCorpInfo().getCorpId();
    String corpName = response.getAuthCorpInfo().getCorpName();

    ThirdCorp thirdCorp = thirdCorpService.get(corpId);
    if (thirdCorp == null) {
      thirdCorp = new ThirdCorp();
      thirdCorp.setId(corpId);
      thirdCorp.setName(corpName);
      thirdCorp.setPermanentCode(permanentCode);
      thirdCorpService.save(thirdCorp);
    } else {
      thirdCorp.setName(corpName);
      thirdCorp.setPermanentCode(permanentCode);
      thirdCorpService.update(thirdCorp);
    }
  }

}
