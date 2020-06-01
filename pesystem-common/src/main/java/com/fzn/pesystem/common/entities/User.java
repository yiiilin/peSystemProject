package com.fzn.pesystem.common.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel("角色")
public class User implements Serializable {
    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("部门id")
    private Integer departId;
    @ApiModelProperty("顶层部门id")
    private Integer topDepartId;
    @ApiModelProperty("总得分")
    private Double score;
}
