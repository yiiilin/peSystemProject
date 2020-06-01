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
@ApiModel("任务进行情况")
public class TaskStatus implements Serializable {

    @ApiModelProperty("任务进行id（自增）")
    private Integer taskStatusId;
    @ApiModelProperty("案件id")
    private Integer caseId;
    @ApiModelProperty("团队id")
    private Integer teamId;
    @ApiModelProperty("任务完成时间")
    private Date taskCompletionTime;
    @ApiModelProperty("任务完成描述")
    private String taskCompletionInfo;
    @ApiModelProperty("附件")
    private String taskCompletionAttachmentsUrl;
    @ApiModelProperty("任务总分")
    private Double taskScore;
    @ApiModelProperty("任务进行情况")
    private String taskStatus;
    @ApiModelProperty("是否在进行成员招募")
    private Integer isRecruiting;

}
