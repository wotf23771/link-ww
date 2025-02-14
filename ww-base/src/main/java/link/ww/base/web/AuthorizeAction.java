package link.ww.base.web;

import link.web.annotation.WrapperWebResult;
import link.ww.base.service.AuthorizeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 认证控制类
 *
 * @author wangxiaolei
 * @since 2025/2/11 18:28
 */
@WrapperWebResult
@Slf4j
@RestController
@RequestMapping
public class AuthorizeAction {

  @Autowired
  private AuthorizeService authorizeService;

  /**
   * 获取企业微信网页授权链接
   * 
   * @param redirectUrl 重定向地址，用户授权后将被重定向到此地址
   * @return 授权链接，用户通过此链接进行授权
   */
  @GetMapping("#{wwBaseProperties.auth.requestAuthorizeUri}")
  public String url(String redirectUrl) {
    String state = UUID.randomUUID().toString(); // 生成随机状态参数，用于防止CSRF攻击
    return authorizeService.getAuthorizeUrl(redirectUrl, state);
  }

  /**
   * 获取企业微信网页授权链接（开放式授权）
   * 
   * @param redirectUrl 重定向地址，用户授权后将被重定向到此地址
   * @return 授权链接，用户通过此链接进行授权以获取详细信息
   */
  @GetMapping("#{wwBaseProperties.auth.requestWebAuthorizeUri}")
  public String webUrl(String redirectUrl) {
    String state = UUID.randomUUID().toString(); // 生成随机状态参数，用于防止CSRF攻击
    return authorizeService.getWebAuthorizeUrl(redirectUrl, state);
  }

}
