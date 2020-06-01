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
@ApiModel("用户角色")
public class UserRole implements Serializable {

    @ApiModelProperty("关联id")
    private Integer id;
    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("角色id")
    private Integer roleId;

}
