package com.geoxus.modules.system.controller.backend;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.annotation.GXRequestBodyToEntityAnnotation;
import com.geoxus.core.common.controller.GXController;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.core.common.validator.group.GXCreateGroup;
import com.geoxus.core.common.validator.group.GXUpdateGroup;
import com.geoxus.modules.system.constant.SRolesConstants;
import com.geoxus.modules.system.entity.SRolesEntity;
import com.geoxus.modules.system.service.SRolesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/role/backend")
public class RoleController implements GXController<SRolesEntity> {
    @Autowired
    private SRolesService rolesService;

    @Override
    @PostMapping("/create")
    @RequiresPermissions("role-create")
    public GXResultUtils create(@Valid @GXRequestBodyToEntityAnnotation(groups = {GXCreateGroup.class}) SRolesEntity target) {
        final long i = rolesService.create(target, Dict.create());
        return GXResultUtils.ok().putData(Dict.create().set(SRolesConstants.PRIMARY_KEY, i));
    }

    @Override
    @PostMapping("/update")
    @RequiresPermissions("role-update")
    public GXResultUtils update(@Valid @GXRequestBodyToEntityAnnotation(groups = {GXUpdateGroup.class}) SRolesEntity target) {
        final long i = rolesService.update(target, Dict.create());
        return GXResultUtils.ok().putData(Dict.create().set(SRolesConstants.PRIMARY_KEY, i));
    }

    @PostMapping("/freeze")
    @RequiresPermissions("role-freeze")
    public GXResultUtils freeze(@RequestBody Dict param) {
        final boolean b = rolesService.freeze(param);
        return GXResultUtils.ok().putData(Dict.create().set("status", b));
    }

    @PostMapping("/unfreeze")
    @RequiresPermissions("role-unfreeze")
    public GXResultUtils unfreeze(@RequestBody Dict param) {
        final boolean b = rolesService.unfreeze(param);
        return GXResultUtils.ok().putData(Dict.create().set("status", b));
    }


    @Override
    @PostMapping("/list-or-search")
    @RequiresPermissions("role-list-or-search")
    public GXResultUtils listOrSearch(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(rolesService.listOrSearchPage(param));
    }

    @Override
    @PostMapping("/detail")
    @RequiresPermissions("role-detail")
    public GXResultUtils detail(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(rolesService.detail(param));
    }
}
