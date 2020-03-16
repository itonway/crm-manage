package com.itontheway.manage.entity.common;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author: 公众号 itontheway
 * @description: 表示每个集合记录自增的序列
 * @date 2020/3/16 21:22
 */
@Document
@Data
public class IncInfo {
    @Id
    private String id;
    private String collectionName;
    private Long incrId;
}
