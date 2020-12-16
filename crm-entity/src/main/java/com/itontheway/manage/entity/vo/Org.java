package com.itontheway.manage.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * (TInfoOrg)表实体类
 *
 * @author ontheway
 * @since 2020-11-27 17:26:01
 */
@Data
@SuppressWarnings("serial")
public class Org {
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
}