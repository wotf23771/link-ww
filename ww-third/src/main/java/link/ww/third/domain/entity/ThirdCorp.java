package link.ww.third.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 安装了应用的企业
 *
 * @author wangxiaolei
 * @since 2025/2/10 11:08
 */
@Data
@TableName(value = "ww_third_corp")
public class ThirdCorp {

  /**
   * 企业 corp_id
   */
  private String id;

  /**
   * 企业 corp_name
   */
  private String name;

  /**
   * 永久授权码
   */
  private String permanentCode;

  private Date createTime;

  /**
   * 授权信息
   */
  private String authInfo;

}
