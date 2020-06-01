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
@ApiModel("团队")
public class Team implements Serializable {

    @ApiModelProperty("团队id")
    private Integer teamId;
    @ApiModelProperty("成员id")
    private Integer uid;
    @ApiModelProperty("成员类型")
    private String userType;

}
