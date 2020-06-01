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
public class Role implements Serializable {

    @ApiModelProperty("角色id")
    private Integer roleId;
    @ApiModelProperty("角色代码")
    private String roleCode;
    @ApiModelProperty("角色描述")
    private String roleInfo;
}
