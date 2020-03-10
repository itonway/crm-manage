package com.itontheway.manage.entity.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Data
public class BaseEntity  implements Serializable {
    private Long id;

    private Long createrId;

    private Long updaterId;

    private String creater;

    private String updater;

    private Date createTime;

    private Date updateTime;

    private String createTimeStr;

    private String createTimeEnd;

    private String updateTimeStr;
}
