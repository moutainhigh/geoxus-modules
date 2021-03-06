package com.geoxus.modules.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.geoxus.core.common.annotation.GXValidateDBExistsAnnotation;
import com.geoxus.core.common.annotation.GXValidateExtDataAnnotation;
import com.geoxus.core.common.entity.GXBaseEntity;
import com.geoxus.core.framework.service.GXCoreModelService;
import com.geoxus.modules.system.constant.SCategoryConstants;
import com.geoxus.modules.system.service.SCategoryService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName(SCategoryConstants.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
public class SCategoryEntity extends GXBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private int categoryId;

    @NotBlank
    private String categoryName;

    @GXValidateDBExistsAnnotation(service = SCategoryService.class, fieldName = "id")
    private int parentId;

    @Min(1)
    private int sort = 1;

    @GXValidateExtDataAnnotation(tableName = "s_category", fieldName = "ext")
    private String ext = "{}";

    private int status = 0;

    @Min(1)
    @GXValidateDBExistsAnnotation(service = GXCoreModelService.class, fieldName = "model_id")
    private int coreModelId;

    private String modelType = "default";

    private String path = "0";
}
