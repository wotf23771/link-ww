package link.ww.auth.service;

import link.ww.auth.domain.AuthorizeScope;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {

  public String getAuthorizeUrl(String state) {
    return getAuthorizeUrl(state, AuthorizeScope.BASE);
  }

  public String getAuthorizeUrl(String state, AuthorizeScope scope) {
    return "redirect:" + state;
  }

  public String getWebAuthorizeUrl(String state) {
    return getWebAuthorizeUrl(state, AuthorizeScope.BASE);
  }

  public String getWebAuthorizeUrl(String state, AuthorizeScope scope) {
    return "redirect:" + state;
  }

}
