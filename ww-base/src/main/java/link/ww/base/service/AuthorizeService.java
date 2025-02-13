package link.ww.base.service;

/**
 * 企业微信OAuth2认证服务接口
 * 用于处理企业微信网页授权，获取用户身份信息
 *
 * <p>有两种实现：
 * <ul>
 *   <li>AgentAuthService: 自建应用的OAuth2认证实现</li>
 *   <li>ThirdAuthorizeService: 第三方应用的OAuth2认证实现</li>
 * </ul>
 *
 * <p>授权流程：
 * <ol>
 *   <li>获取认证链接</li>
 *   <li>用户访问链接并同意授权</li>
 *   <li>企业微信回调并携带code</li>
 *   <li>通过code获取用户信息</li>
 * </ol>
 *
 * @author wangxiaolei
 * @see link.ww.agent.service.AgentAuthService
 * @see link.ww.third.service.ThirdAuthorizeService
 * @since 2025/2/10
 */
public interface AuthorizeService {

  /**
   * 获取企业微信网页授权链接
   * 用于获取成员的基础信息（UserId）
   *
   * @param redirectUrl 重定向地址
   * @param state       重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值，长度不可超过128个字节
   * @return 授权链接
   */
  String getAuthorizeUrl(String redirectUrl, String state);

  /**
   * 获取企业微信网页授权链接（开放式授权）
   * 用于获取成员的详细信息（手机号、邮箱等）
   *
   * @param redirectUrl 重定向地址
   * @param state       重定向后会带上state参数，企业可以填写a-zA-Z0-9的参数值，长度不可超过128个字节
   * @return 授权链接
   */
  String getWebAuthorizeUrl(String redirectUrl, String state);

}
