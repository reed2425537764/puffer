package cn.syq.puffer.manager.model.api.field;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/20 17:29
 */
@Data
public class FieldDelete {

    @NotNull
    @Min(1L)
    private Long id;

    @NotNull
    @Min(1L)
    private Long projectId;

    @NotNull
    @Min(1L)
    private Long doId;

    @NotBlank
    @Size(max = 90)
    private String remark;
}
