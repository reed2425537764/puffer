package cn.syq.puffer.manager.model.api.field;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * ***
 *
 * @author shiyuqin
 * @date 2022/09/20 15:43
 */
@Data
public class FieldEdit {

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
    @Size(max = 32)
    private String name;

    @NotBlank
    @Size(max = 60)
    private String label;

    @Pattern(regexp = "[1-8]")
    private String type;

    @Size(max = 90)
    private String description;

    @NotNull
    private Boolean listFlag;
}
