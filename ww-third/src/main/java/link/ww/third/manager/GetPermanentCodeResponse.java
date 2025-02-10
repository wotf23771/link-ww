package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

/**
 * 获取企业永久授权码 响应
 *
 * @author wangxiaolei
 * @since 2025/2/10 10:33
 */
@Data
public class GetPermanentCodeResponse extends BaseResponse {

  /**
   * 企业微信永久授权码,最长为512字节
   */
  @JsonProperty("permanent_code")
  private String permanentCode;

  /**
   * 授权方企业信息
   */
  @JsonProperty("auth_corp_info")
  private AuthCorpInfo authCorpInfo;

  /**
   * 授权管理员的信息，可能不返回
   */
  @JsonProperty("auth_user_info")
  private AuthUserInfo authUserInfo;

  /**
   * 推广二维码安装相关信息，扫推广二维码安装时返回。<br/>
   * 成员授权时暂不支持。<br/>
   * （注：无论企业是否新注册，只要通过扫推广二维码安装，都会返回该字段）
   */
  @JsonProperty("register_code_info")
  private RegisterCodeInfo registerCodeInfo;

  /**
   * 授权方企业信息
   */
  @Data
  public static class AuthCorpInfo {

    /**
     * 授权方企业微信id
     */
    @JsonProperty("corpid")
    private String corpId;

    /**
     * 授权方企业名称，即企业简称
     */
    @JsonProperty("corp_name")
    private String corpName;

  }

  /**
   * 授权管理员的信息
   */
  @Data
  public static class AuthUserInfo {

    /**
     * 授权管理员的userid，可能为空
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 授权管理员的open_userid，可能为空
     */
    @JsonProperty("open_userid")
    private String openUserId;

    /**
     * 授权管理员的name，可能为空
     */
    private String name;

    /**
     * 授权管理员的头像url，可能为空
     */
    private String avatar;

  }

  /**
   * 推广二维码安装相关信息
   */
  @Data
  public static class RegisterCodeInfo {

    /**
     * 注册码
     */
    @JsonProperty("register_code")
    private String registerCode;

    /**
     * 推广包ID
     */
    @JsonProperty("template_id")
    private String templateId;

    /**
     * 仅当获取注册码指定该字段时才返回
     */
    private String state;

  }

}
