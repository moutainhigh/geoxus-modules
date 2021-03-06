package com.geoxus.modules.contents.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geoxus.core.common.annotation.GXValidateDBExistsAnnotation;
import com.geoxus.core.common.annotation.GXValidateExtDataAnnotation;
import com.geoxus.core.common.entity.GXBaseEntity;
import com.geoxus.core.framework.service.GXCoreModelService;
import com.geoxus.modules.contents.constant.FeedBackConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@TableName(FeedBackConstants.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
public class FeedBackEntity extends GXBaseEntity {
    @TableId
    private int feedbackId;

    private int parentId;

    private int feedbackType;

    @GXValidateDBExistsAnnotation(service = GXCoreModelService.class, fieldName = "model_id")
    private int coreModelId;

    private long objectId;

    @GXValidateExtDataAnnotation(tableName = "p_feedback", fieldName = "ext")
    private String ext;

    private long userId;

    private int status;
}
