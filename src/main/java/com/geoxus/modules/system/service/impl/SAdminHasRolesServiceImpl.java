package com.geoxus.modules.system.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.geoxus.modules.system.constant.SAdminConstants;
import com.geoxus.modules.system.constant.SRolesConstants;
import com.geoxus.modules.system.entity.SAdminEntity;
import com.geoxus.modules.system.entity.SAdminHasRolesEntity;
import com.geoxus.modules.system.entity.SRolesEntity;
import com.geoxus.modules.system.mapper.SAdminHasRolesMapper;
import com.geoxus.modules.system.service.SAdminHasRolesService;
import com.geoxus.modules.system.service.SAdminService;
import com.geoxus.modules.system.service.SRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SAdminHasRolesServiceImpl extends ServiceImpl<SAdminHasRolesMapper, SAdminHasRolesEntity> implements SAdminHasRolesService {
    @Autowired
    private SRolesService sRolesService;

    @Autowired
    private SAdminService sAdminService;

    @Override
    public Set<String> getAdminRoles(long adminId) {
        return baseMapper.getRoleNameByAdminId(adminId);
    }

    @Override
    public boolean addRoleToAdmin(long adminId, List<Long> roleIds) {
        final Integer sAdminExists = sAdminService.checkRecordIsExists(SAdminEntity.class, Dict.create().set(SAdminConstants.PRIMARY_KEY, adminId));
        if (null == sAdminExists) {
            return false;
        }
        boolean remove = true;
        final QueryWrapper<SAdminHasRolesEntity> deleteQuery = new QueryWrapper<SAdminHasRolesEntity>().eq(SAdminConstants.PRIMARY_KEY, adminId);
        if (null != checkRecordIsExists(SAdminHasRolesEntity.class, Dict.create().set(SAdminConstants.PRIMARY_KEY, adminId))) {
            remove = remove(deleteQuery);
        }
        if (remove) {
            final ArrayList<SAdminHasRolesEntity> addListEntity = CollUtil.newArrayList();
            for (Long roleId : roleIds) {
                final Integer sRoleExists = sRolesService.checkRecordIsExists(SRolesEntity.class, Dict.create().set(SRolesConstants.PRIMARY_KEY, roleId));
                if (null == sRoleExists) {
                    continue;
                }
                final SAdminHasRolesEntity entity = new SAdminHasRolesEntity();
                entity.setAdminId(adminId);
                entity.setRoleId(roleId);
                addListEntity.add(entity);
            }
            if (!addListEntity.isEmpty()) {
                return saveBatch(addListEntity);
            }
        }
        return false;
    }
}
