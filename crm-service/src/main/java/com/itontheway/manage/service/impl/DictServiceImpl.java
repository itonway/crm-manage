package com.itontheway.manage.service.impl;

import com.itontheway.manage.service.IDictService;
import org.springframework.stereotype.Service;

/**
 * @author: 公众号 itontheway
 * @description: TODO
 * @date 2020/3/9 12:21
 */
@Service
public class DictServiceImpl extends BaseMongodbServiceImpl<Dict> implements IDictService {

    @Override
    protected Class<Dict> getEntityClass() {
        return Dict.class;
    }

}
