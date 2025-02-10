package link.ww.base.service;

/**
 * AccessToken 服务
 */
public interface AccessTokenService {

  /**
   * 获取当前上下文中的access_token <br/>
   * 若当前是第三方应用，仅允许在主线程访问
   *
   * @return
   */
  String getAccessToken();

}
