package ${client_common_package};


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public abstract class AbstractDTO {

    @Schema(description = "id", hidden = true)
    private Long id;

    @Schema(description = "创建时间", hidden = true)
    private Date createdAt;

    @Schema(description = "创建人", hidden = true)
    private String createdBy;

    @Schema(description = "更新时间", hidden = true)
    private Date modifiedAt;

    @Schema(description = "更新人", hidden = true)
    private String modifiedBy;

    @Schema(description = "是否逻辑删除", hidden = true)
    private Boolean isDeleted;

    @Schema(description = "乐观锁版本号", hidden = true)
    private Integer version;

}
