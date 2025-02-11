package link.ww.third.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 已授权的企业信息
 * 存储安装了第三方应用的企业基本信息和授权状态
 *
 * <p>表名: ww_third_corp
 * 
 * @author wangxiaolei
 * @since 2025/2/10
 */
@Data
@TableName(value = "ww_third_corp")
public class ThirdCorp {

    /**
     * 企业ID，即企业微信的corpid
     * 主键，最大长度64字符
     */
    private String id;

    /**
     * 企业名称，即企业简称
     * 最大长度100字符
     */
    private String name;

    /**
     * 企业永久授权码
     * 用于获取企业的access_token，最大长度512字符
     */
    private String permanentCode;

    /**
     * 记录创建时间
     */
    private Date createTime;

    /**
     * 企业的授权信息，JSON格式
     * 包含应用权限、部门权限等信息
     */
    private String authInfo;
}
