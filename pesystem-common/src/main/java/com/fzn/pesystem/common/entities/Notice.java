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
@ApiModel("绩效考核通知")
public class Notice implements Serializable {

    @ApiModelProperty("通知id")
    private Integer noticeId;
    @ApiModelProperty("通知标题")
    private String noticeTitle;
    @ApiModelProperty("通知内容")
    private String noticeInfo;
    @ApiModelProperty("附件")
    private String noticeAttachmentsUrl;
    @ApiModelProperty("创建时间")
    private Date updateTime;

}
