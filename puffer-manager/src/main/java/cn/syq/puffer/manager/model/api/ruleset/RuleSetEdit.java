package cn.syq.puffer.manager.model.api.ruleset;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/27 16:19
 */
@Data
public class RuleSetEdit {

    @NotNull
    @Min(1L)
    private Long id;

    @NotNull
    @Min(1L)
    private Long projectId;

    @NotNull
    @Min(1L)
    private Long rsCatalogId;

    @NotBlank
    @Size(max = 60)
    private String label;
}
