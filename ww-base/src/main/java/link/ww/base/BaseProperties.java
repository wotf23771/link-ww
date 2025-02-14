package link.ww.base;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 企业微信基础配置属性
 * 用于配置企业微信的基本参数和应用信息
 *
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Slf4j
@Data
@Component("wwBaseProperties")
@ConfigurationProperties(prefix = BaseConstant.CONFIG_PREFIX)
public class BaseProperties implements InitializingBean {

  /**
   * 企业微信API接口基础URL
   * 例如: https://qyapi.weixin.qq.com/cgi-bin
   */
  private String baseUrl;

  /**
   * 企业微信企业ID
   * 每个企业都拥有唯一的corpId
   */
  private String corpId;

  /**
   * 应用类型
   * INNER: 自建应用
   * THIRD: 第三方应用
   */
  private AgentType agentType;

  /**
   * 默认应用标识
   * 用于获取默认的access_token
   */
  private String defaultAgent;

  /**
   * 通讯录同步应用标识
   * 用于同步企业通讯录数据
   */
  private String contactsSyncAgent;

  private String corpSignatureUri;

  private String agentSignatureUri;

  /**
   * OAuth2授权配置
   */
  private Auth auth;

  @Data
  public static class Auth {

    private String requestAuthorizeUri;

    private String requestWebAuthorizeUri;

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    log.debug("baseUrl:{}", baseUrl);
    log.debug("corpId:{}", corpId);
    log.debug("agentType:{}", agentType);
    log.debug("defaultAgent:{}", defaultAgent);
    log.debug("contactsSyncAgent:{}", contactsSyncAgent);
  }

}
