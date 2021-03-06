package com.geoxus.modules.system.mapper;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.geoxus.core.common.mapper.GXBaseMapper;
import com.geoxus.modules.system.builder.SMenuBuilder;
import com.geoxus.modules.system.entity.SMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface SMenuMapper extends GXBaseMapper<SMenuEntity> {
    @SelectProvider(type = SMenuBuilder.class, method = "detail")
    Dict detail(Dict param);

    @SelectProvider(type = SMenuBuilder.class, method = "listOrSearch")
    List<Dict> listOrSearch(Dict param);

    @SelectProvider(type = SMenuBuilder.class, method = "listOrSearch")
    List<Dict> listOrSearchPage(IPage<Dict> page, Dict param);

    @SelectProvider(type = SMenuBuilder.class, method = "getAllMenuId")
    List<Integer> getAllMenuId(Long adminId);

    @SelectProvider(type = SMenuBuilder.class, method = "getRoleAllPerms")
    List<Dict> getRoleAllPerms(String roles);

    @SelectProvider(type = SMenuBuilder.class, method = "getAdminAllPerms")
    List<Dict> getAdminAllPerms(Long adminId);
}
