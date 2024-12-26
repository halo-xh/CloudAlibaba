package ${client_common_package};


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public abstract class AbstractVO {

    @Schema(description = "id", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "创建时间")
    private Date createdAt;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "更新时间")
    private Date modifiedAt;

    @Schema(description = "更新人")
    private String modifiedBy;

    @Schema(description = "是否逻辑删除")
    private Boolean isDeleted;

    @Schema(description = "乐观锁版本号")
    private Integer version;

}
