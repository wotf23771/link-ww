package link.ww.base;

/**
 * 企业微信授权范围枚举
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
public enum AuthorizeScope {
  /**
   * 静默授权
   */
  BASE("snsapi_base"),

  /**
   * 手动授权
   */
  PRIVATE_INFO("snsapi_privateinfo");

  private String scope;

  AuthorizeScope(String scope) {
    this.scope = scope;
  }

  public String getScope() {
    return scope;
  }
}
