package com.geoxus.modules.banner.controller.backend;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.annotation.GXRequestBodyToEntityAnnotation;
import com.geoxus.core.common.controller.GXController;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.core.common.vo.GXBusinessStatusCode;
import com.geoxus.modules.banner.constant.BannerConstants;
import com.geoxus.modules.banner.entity.BannerEntity;
import com.geoxus.modules.banner.service.BannerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("backendBannerController")
@RequestMapping("/banner/backend")
public class BannerController implements GXController<BannerEntity> {
    @Autowired
    private BannerService bannerService;

    @Override
    @PostMapping("/create")
    @RequiresPermissions("banner-create")
    public GXResultUtils create(@Valid @GXRequestBodyToEntityAnnotation BannerEntity bannerEntity) {
        long i = bannerService.create(bannerEntity, Dict.create());
        return GXResultUtils.ok().putData(Dict.create().set(BannerConstants.PRIMARY_KEY, i));
    }

    @Override
    @PostMapping("/update")
    @RequiresPermissions("banner-update")
    public GXResultUtils update(@Valid @GXRequestBodyToEntityAnnotation BannerEntity bannerEntity) {
        long i = bannerService.update(bannerEntity, Dict.create());
        return GXResultUtils.ok().putData(Dict.create().set(BannerConstants.PRIMARY_KEY, i));
    }

    @Override
    @PostMapping("/delete")
    @RequiresPermissions("banner-delete")
    public GXResultUtils delete(@RequestBody Dict param) {
        final boolean b = bannerService.delete(param);
        return GXResultUtils.ok().putData(Dict.create().set("status", b));
    }

    @Override
    @PostMapping("/list-or-search")
    @RequiresPermissions("banner-list-or-search")
    public GXResultUtils listOrSearch(@RequestBody Dict param) {
        param.set("status", GXBusinessStatusCode.NORMAL.getCode());
        return GXResultUtils.ok().putData(bannerService.listOrSearchPage(param));
    }

    @Override
    @PostMapping("/detail")
    @RequiresPermissions("banner-detail")
    public GXResultUtils detail(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(bannerService.detail(param));
    }

    @PostMapping("/show")
    @RequiresPermissions("banner-show")
    public GXResultUtils show(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(Dict.create().set("status", bannerService.show(param)));
    }

    @PostMapping("/hidden")
    @RequiresPermissions("banner-hidden")
    public GXResultUtils hidden(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(Dict.create().set("status", bannerService.hidden(param)));
    }
}
