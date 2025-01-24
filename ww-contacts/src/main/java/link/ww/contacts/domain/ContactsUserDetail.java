package link.ww.contacts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContactsUserDetail extends ContactsUser {

  private List<Integer> order;

  @JsonProperty("is_leader_in_dept")
  private List<Integer> isLeaderInDept;

  @JsonProperty("direct_leader")
  private List<String> directLeader;

  private String position;

  private String mobile;

  private String gender;

  private String email;

  @JsonProperty("biz_mail")
  private String bizEmail;

  private String avatar;

  @JsonProperty("thumb_avatar")
  private String thumbAvatar;

  private String telephone;

  private String alias;

  /**
   * 激活状态: 1=已激活，2=已禁用，4=未激活，5=退出企业。<br/>
   * 已激活代表已激活企业微信或已关注微信插件（原企业号）。未激活代表既未激活企业微信又未关注微信插件（原企业号）。
   */
  private Integer status;

  private String address;

  @JsonProperty("english_name")
  private String englishName;

  @JsonProperty("main_department")
  private Integer mainDept;

  @JsonProperty("qr_code")
  private String qrCode;

  @JsonProperty("external_position")
  private String externalPosition;

}
