package link.ww.base;

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
