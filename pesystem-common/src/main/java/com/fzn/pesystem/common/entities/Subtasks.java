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
@ApiModel("子任务")
public class Subtasks implements Serializable {

    @ApiModelProperty("子任务id")
    private Integer subtasksId;
    @ApiModelProperty("团队id")
    private Integer teamId;
    @ApiModelProperty("指派成员id")
    private Integer uid;
    @ApiModelProperty("子任务描述")
    private String subtasksDesciption;
    @ApiModelProperty("附件")
    private String subtasksAttachmentsUrl;
    @ApiModelProperty("创建时间")
    private Date updateTime;
    @ApiModelProperty("任务完成权重")
    private Double weights;
    @ApiModelProperty("是否有提交结果")
    private Integer isSubmit;
    @ApiModelProperty("是否已完成")
    private Integer isCompleted;


}
