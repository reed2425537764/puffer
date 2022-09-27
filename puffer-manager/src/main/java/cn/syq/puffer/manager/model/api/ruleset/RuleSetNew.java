package cn.syq.puffer.manager.model.api.ruleset;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/22 14:45
 */
@Data
public class RuleSetNew {

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
