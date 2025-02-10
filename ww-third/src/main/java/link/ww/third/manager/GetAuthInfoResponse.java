package link.ww.third.manager;

import com.fasterxml.jackson.annotation.JsonProperty;
import link.ww.base.domain.BaseResponse;
import lombok.Data;

import java.util.List;

/**
 * 获取企业授权信息 响应
 *
 * @author wangxiaolei
 * @since 2025/2/10 13:56
 */
@Data
public class GetAuthInfoResponse extends BaseResponse {

  @JsonProperty("auth_corp_info")
  private AuthCorpInfo authCorpInfo;

  @JsonProperty("auth_info")
  private AuthInfo authInfo;

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

    /**
     * 授权方企业类型，认证号：verified, 注册号：unverified
     */
    @JsonProperty("corp_type")
    private String corpType;

    /**
     * 授权方企业方形头像
     */
    @JsonProperty("corp_square_logo_url")
    private String corpSquareLogoUrl;

    /**
     * 授权方企业用户规模
     */
    @JsonProperty("corp_user_max")
    private Integer corpUserMax;

    /**
     * 授权方企业的主体名称(仅认证或验证过的企业有)，即企业全称。<br/>
     * 企业微信将逐步回收该字段，后续实际返回内容为企业名称，即auth_corp_info.corp_name。
     */
    @JsonProperty("corp_full_name")
    private String corpFullName;

    /**
     * 认证到期时间
     */
    @JsonProperty("verified_end_time")
    private Long verifiedEndTime;

    /**
     * 企业类型，1. 企业; 2. 政府以及事业单位; 3. 其他组织, 4.团队号
     */
    @JsonProperty("subject_type")
    private Integer subjectType;

    /**
     * 企业规模。当企业未设置该属性时，值为空
     */
    @JsonProperty("corp_scale")
    private String corpScale;

    /**
     * 企业所属行业。当企业未设置该属性时，值为空
     */
    @JsonProperty("corp_industry")
    private String corpIndustry;

    /**
     * 企业所属子行业。当企业未设置该属性时，值为空
     */
    @JsonProperty("corp_sub_industry")
    private String corpSubIndustry;

    /**
     * 企业其他认证的名称，仅认证企业才有
     */
    @JsonProperty("corp_ex_name")
    private CorpExName corpExName;

  }

  @Data
  public static class CorpExName {

    @JsonProperty("name_list")
    private String nameList;

  }

  /**
   * 授权信息。如果是通讯录应用，且没开启实体应用，是没有该项的。通讯录应用拥有企业通讯录的全部信息读写权限
   */
  @Data
  public static class AuthInfo {

    /**
     * 授权的应用信息，注意是一个数组，但仅旧的多应用套件授权时会返回多个agent，对新的单应用授权，永远只返回一个agent
     */
    private List<AuthAgent> agent;

  }

  @Data
  public static class AuthAgent {

    /**
     * 授权方应用id
     */
    @JsonProperty("agentid")
    private Integer agentId;

    /**
     * 授权方应用名字
     */
    private String name;

    /**
     * 授权方应用圆形头像
     */
    @JsonProperty("round_logo_url")
    private String roundLogoUrl;

    /**
     * 授权方应用方形头像
     */
    @JsonProperty("square_logo_url")
    private String squareLogoUrl;

    /**
     * 旧的多应用套件中的对应应用id，新开发者请忽略
     */
    @JsonProperty("appid")
    private Integer appId;

    /**
     * 授权模式，0为管理员授权；1为成员授权
     */
    @JsonProperty("auth_mode")
    private Integer authMode;

    /**
     * 是否为代开发自建应用
     */
    @JsonProperty("is_customized_app")
    private Boolean isCustomizedApp;

    /**
     * 来自第三方应用接口唤起,仅通过第三方应用添加自建应用 获取授权链接授权代开发自建应用时，才返回该字段
     */
    @JsonProperty("auth_from_thirdapp")
    private Boolean authFromThirdApp;

    /**
     * 应用对应的权限
     */
    private AuthPrivilege privilege;

    /**
     * 共享了应用的企业信息，仅当由企业互联或者上下游共享应用触发的安装时才返回
     */
    @JsonProperty("shared_from")
    private AuthSharedFrom sharedFrom;

  }

  @Data
  public static class AuthPrivilege {

    /**
     * 应用可见范围（部门）
     */
    @JsonProperty("allow_party")
    private List<Integer> allowParty;

    /**
     * 应用可见范围（标签）
     */
    @JsonProperty("allow_tag")
    private List<Integer> allowTag;

    /**
     * 应用可见范围（成员）
     */
    @JsonProperty("allow_user")
    private List<String> allowUser;

    /**
     * 额外通讯录（部门）
     */
    @JsonProperty("extra_party")
    private List<Integer> extraParty;

    /**
     * 额外通讯录（成员）
     */
    @JsonProperty("extra_user")
    private List<String> extraUser;

    /**
     * 额外通讯录（标签）
     */
    @JsonProperty("extra_tag")
    private List<Integer> extraTag;

    /**
     * 权限等级。<br/>
     * 1:通讯录基本信息只读<br/>
     * 2:通讯录全部信息只读<br/>
     * 3:通讯录全部信息读写<br/>
     * 4:单个基本信息只读<br/>
     * 5:通讯录全部信息只写
     */
    private Integer level;

  }

  @Data
  public static class AuthSharedFrom {

    /**
     * 共享了应用的企业信息，仅当企业互联或者上下游共享应用触发的安装时才返回
     */
    @JsonProperty("corpid")
    private String corpId;

    /**
     * 共享了途径，0表示企业互联，1表示上下游
     */
    @JsonProperty("share_type")
    private Integer shareType;

  }

}
