package org.devops.mjar.message.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.devops.core.model.annotation.Column;
import org.devops.core.model.annotation.Table;
import org.devops.core.utils.util.ResourceUtil;
import org.devops.core.utils.verify.VerifyField;
import org.devops.core.utils.vo.BaseBean;

import java.util.ArrayList;
import java.util.List;

@Table(table = "message_email", create = true, comment = "邮箱配置", parametric = true)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮箱配置")
public class MessageEmail extends BaseBean {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    public static final String ID = "id";
    /**
     * 伙伴ID
     */
    public static final String PARTNER_ID = "partner_id";
    /**
     * 服务商
     */
    public static final String HOST = "host";
    /**
     * 端口
     */
    public static final String PORT = "port";
    /**
     * 协议
     */
    public static final String PROTOCOL = "protocol";
    /**
     * 用户名
     */
    public static final String USERNAME = "username";
    /**
     * 昵称
     */
    public static final String NICKNAME = "nickname";
    /**
     * 密码，请先用工具加密
     */
    public static final String PASSWD = "passwd";
    /**
     * 是否激活  0否 1是
     */
    public static final String ACTIVE = "active";

    @SuppressWarnings("serial")
    public static List<MessageEmail> init() {
        return new ArrayList<MessageEmail>() {{
        }};
    }

    public static String initSQL() {
        return ResourceUtil.readResourceAsString("classpath:sql/devops-mjar-message/message_email.sql");
    }

    /**
     * 流水号
     */
    @Column(name = "id", jdbcType = "bigint(20)", priKey = true, autoIncrement = true, comment = "流水号")
    @ApiModelProperty("流水号")
    @VerifyField(value = "流水号", ignore = true)
    private Long id;

    /**
     * 伙伴ID
     */
    @Column(name = "partner_id", jdbcType = "bigint(20)", notNull = true, def = "'-1'", extra = "", comment = "伙伴ID")
    @ApiModelProperty("伙伴ID")
    @VerifyField(value = "伙伴ID", ignore = true)
    private Long partnerId;

    /**
     * 服务商
     */
    @Column(name = "host", jdbcType = "varchar(200)", notNull = true, def = "''", extra = "", comment = "服务商")
    @ApiModelProperty("服务商")
    @VerifyField(value = "服务商", ignore = true)
    private String host;

    /**
     * 协议
     */
    @Column(name = "protocol", jdbcType = "varchar(100)", notNull = true, def = "''", extra = "", comment = "协议")
    @ApiModelProperty("协议")
    @VerifyField(value = "协议", ignore = true)
    private String protocol;

    /**
     * 端口
     */
    @Column(name = "port", jdbcType = "int(11)", notNull = true, def = "25", extra = "", comment = "端口")
    @ApiModelProperty("端口")
    @VerifyField(value = "端口", ignore = true)
    private Integer port;

    /**
     * 用户名
     */
    @Column(name = "username", jdbcType = "varchar(100)", notNull = true, def = "''", extra = "", comment = "用户名")
    @ApiModelProperty("用户名")
    @VerifyField(value = "用户名", ignore = true)
    private String username;

    /**
     * 用户名
     */
    @Column(name = "nickname", jdbcType = "varchar(100)", notNull = true, def = "''", extra = "", comment = "昵称")
    @ApiModelProperty("昵称")
    @VerifyField(value = "昵称", ignore = true)
    private String nickname;

    /**
     * 密码，请先用工具加密
     */
    @Column(name = "passwd", jdbcType = "varchar(200)", notNull = true, def = "''", extra = "", comment = "密码，请先用工具加密")
    @ApiModelProperty("密码，请先用工具加密")
    @VerifyField(value = "密码，请先用工具加密", ignore = true)
    private String passwd;

    /**
     * 密码，请先用工具加密
     */
    @Column(name = "active", jdbcType = "tinyint(4)", notNull = true, def = "1", extra = "", comment = " 是否激活  0否 1是")
    @ApiModelProperty("是否激活  0否 1是")
    @VerifyField(value = "是否激活  0否 1是", ignore = true)
    private String active;

}
