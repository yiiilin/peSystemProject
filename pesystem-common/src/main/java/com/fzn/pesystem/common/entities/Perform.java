package com.fzn.pesystem.common.entities;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
@ApiModel("个人绩效")
public class Perform implements Serializable {

    @ApiModelProperty("绩效id")
    private Integer performId;
    @ApiModelProperty("用户id")
    private Integer uid;
    @ApiModelProperty("部门id")
    private Integer departId;
    @ApiModelProperty("绩效得分")
    private Double performScore;
    @ApiModelProperty("录入时间")
    private Date updateTime;
}
