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
@ApiModel("部门")
public class Depart implements Serializable {

    @ApiModelProperty("部门id")
    private Integer departId;
    @ApiModelProperty("父部门id")
    private Integer parentDepartId;
    @ApiModelProperty("部门名称")
    private String departName;
    @ApiModelProperty("部门描述")
    private String departInfo;
    @ApiModelProperty("部门地址")
    private String departAddress;
    @ApiModelProperty("部门负责人id")
    private Integer departAdminId;

}
