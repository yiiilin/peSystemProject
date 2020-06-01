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
@ApiModel("案件")
public class Case implements Serializable {
    @ApiModelProperty("案件id")
    private Integer caseId;
    @ApiModelProperty("案件类型id")
    private Integer caseTypeId;
    @ApiModelProperty("案件种类名称")
    private String caseTypeName;
    @ApiModelProperty("案件详细描述")
    private String caseInfo;
    @ApiModelProperty("案件附件地址")
    private String caseAttachmentsUrl;
    @ApiModelProperty("案件上报时间")
    private Date caseUpdateTime;
    @ApiModelProperty("案件当前状态")
    private String caseStatus;
    @ApiModelProperty("审核时间")
    private Date reviewTime;


}
