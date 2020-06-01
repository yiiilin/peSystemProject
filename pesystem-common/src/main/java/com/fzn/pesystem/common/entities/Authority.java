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
@ApiModel("权限")
public class Authority implements Serializable {
    @ApiModelProperty("权限id")
    private Integer authorityId;
    @ApiModelProperty("权限code")
    private String authorityCode;
    @ApiModelProperty("权限信息")
    private String authorityInfo;

}
