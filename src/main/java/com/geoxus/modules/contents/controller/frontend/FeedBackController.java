package com.geoxus.modules.contents.controller.frontend;

import cn.hutool.core.lang.Dict;
import com.geoxus.core.common.annotation.GXLoginAnnotation;
import com.geoxus.core.common.annotation.GXRequestBodyToEntityAnnotation;
import com.geoxus.core.common.controller.GXController;
import com.geoxus.core.common.oauth.GXTokenManager;
import com.geoxus.core.common.util.GXResultUtils;
import com.geoxus.modules.contents.constant.FeedBackConstants;
import com.geoxus.modules.contents.entity.FeedBackEntity;
import com.geoxus.modules.contents.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("frontendFeedBackController")
@RequestMapping("/feedback/frontend")
public class FeedBackController implements GXController<FeedBackEntity> {
    @Autowired
    private FeedBackService feedBackService;

    @Override
    @PostMapping("/create")
    @GXLoginAnnotation
    public GXResultUtils create(@Valid @GXRequestBodyToEntityAnnotation FeedBackEntity target) {
        final long userId = getUserIdFromToken(GXTokenManager.USER_TOKEN, GXTokenManager.USER_ID);
        target.setUserId(userId);
        final long i = feedBackService.create(target, Dict.create());
        return GXResultUtils.ok().putData(Dict.create().set(FeedBackConstants.PRIMARY_KEY, i));
    }

    @Override
    @PostMapping("/list-or-search")
    @GXLoginAnnotation
    public GXResultUtils listOrSearch(@RequestBody Dict param) {
        param.set("user_id", getUserIdFromToken(GXTokenManager.USER_TOKEN, GXTokenManager.USER_ID));
        return GXResultUtils.ok().putData(feedBackService.listOrSearchPage(param));
    }

    @Override
    @PostMapping("/detail")
    @GXLoginAnnotation
    public GXResultUtils detail(@RequestBody Dict param) {
        return GXResultUtils.ok().putData(feedBackService.detail(param));
    }
}
