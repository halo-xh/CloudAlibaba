package ${infra_common_package};


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public abstract class AbstractEntity {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @TableField(value = "id")
    private Long id;

    @Schema(description = "创建时间")
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @Schema(description = "创建人")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    @Schema(description = "更新时间")
    @TableField(value = "modified_at", fill = FieldFill.INSERT_UPDATE)
    private Date modifiedAt;

    @Schema(description = "更新人")
    @TableField(value = "modified_by", fill = FieldFill.INSERT_UPDATE)
    private String modifiedBy;

    @Schema(description = "是否逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean isDeleted;

    @Schema(description = "乐观锁版本号")
    @TableField("version")
    @Version
    private Integer version;

}
