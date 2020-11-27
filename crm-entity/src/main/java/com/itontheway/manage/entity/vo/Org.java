package com.itontheway.manage.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (TInfoOrg)表实体类
 *
 * @author ontheway
 * @since 2020-11-27 17:26:01
 */
@Data
@SuppressWarnings("serial")
public class Org extends Model<Org> {
 @TableId(type = IdType.AUTO)
    /**ID*/
  private Long id;
    
  private String orgName;
    /**code*/
  private String orgCode;
    
  private String orgPname;
    
  private String orgPcode;
    
  private String state;
    
  private Date createTime;
    
  private String creater;
    
  private Date updateTime;
    
  private String updater;


  /**
   * 获取主键值
   *
   * @return 主键值
   */
  @Override
  protected Serializable pkVal() {
    return this.id;
  }
  }