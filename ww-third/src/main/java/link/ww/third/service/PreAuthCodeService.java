package link.ww.third.service;

import link.ww.third.manager.GetPreAuthCodeResponse;
import link.ww.third.manager.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预授权码服务
 *
 * @author wangxiaolei
 * @since 2025/2/10 10:16
 */
@Service
public class PreAuthCodeService {

  @Autowired
  private ServiceManager serviceManager;

  @Autowired
  private SuiteTokenService suiteTokenService;

  public String getPreAuthCode() {
    String token = suiteTokenService.getToken();
    GetPreAuthCodeResponse response = serviceManager.getPreAuthCode(token);
    if (response != null) {
      return response.getPreAuthCode();
    }
    return null;
  }

}
