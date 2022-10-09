package cn.syq.puffer.manager.model.api.rule;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/29 14:33
 */
@Data
public class RuleNew {

    @NotNull
    private Long projectId;

    @NotNull
    private Long rsId;

    @NotBlank
    private String name;

    @NotBlank
    private String label;

    @NotBlank
    @Pattern(regexp = "[0-6]")
    private String type;
}
