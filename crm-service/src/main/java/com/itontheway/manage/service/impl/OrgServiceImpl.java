package com.itontheway.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itontheway.manage.dao.OrgDao;
import com.itontheway.manage.entity.vo.Org;
import com.itontheway.manage.service.IOrgService;
import org.springframework.stereotype.Service;

/**
 * @Author: xiegl
 * @desc
 * @Date 2020-11-27 17:31
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgDao, Org> implements IOrgService {
}
