package com.itontheway.manage.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_info_org
 * @author 
 */
@Data
public class Org implements Serializable {
    /**
     * ID
     */
    private Long id;

    private String orgName;

    /**
     * code
     */
    private String orgCode;

    private String orgPname;

    private String orgPcode;

    private String state;

    private Date createTime;

    private String creater;

    private Date updateTime;

    private String updater;

    private static final long serialVersionUID = 1L;
}