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
@ApiModel("案件类型")
public class CaseType implements Serializable {
    @ApiModelProperty("案件类型id")
    private Integer caseTypeId;
    @ApiModelProperty("案件种类名称")
    private String caseTypeName;
    @ApiModelProperty("案件描述")
    private String caseTypeInfo;
    @ApiModelProperty("预估总分")
    private Double estimatedScore;


}
