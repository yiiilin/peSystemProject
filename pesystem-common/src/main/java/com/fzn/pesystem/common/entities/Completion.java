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
@ApiModel("个人任务完成情况")
public class Completion implements Serializable {
    @ApiModelProperty("工作统计id")
    private Integer completionId;
    @ApiModelProperty("子任务id")
    private Integer subtasksId;
    @ApiModelProperty("成员id")
    private Integer uid;
    @ApiModelProperty("工作量描述")
    private String completionInfo;
    @ApiModelProperty("视频链接")
    private String completionVideoUrl;
    @ApiModelProperty("附件")
    private String completionAttachmentsUrl;
    @ApiModelProperty("提交时间")
    private Date updateTime;

}
