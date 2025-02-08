package link.ww.base.service;

/**
 * 认证服务
 */
public interface AuthorizeService {

  /**
   * 获得企微认证登录地址
   *
   * @param state
   * @return
   */
  String getAuthorizeUrl(String state);

  /**
   * 获得WEB认证登录地址
   *
   * @param state
   * @return
   */
  String getWebAuthorizeUrl(String state);

}
