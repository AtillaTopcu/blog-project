package com.atillatpc.business.dto;

import com.atillatpc.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDto extends AuditingAwareBaseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1l; // Serial

    private Long blogId;
    @NotEmpty(message = "{blog.header.least.validation.constraints.NotNull.message}")
    @Size(min = 3, message = "{blog.header.least.validation.constraints.NotNull.message}")
    private String header;
    @NotEmpty(message = "{blog.title.least.validation.constraints.NotNull.message}")
    private String title;
    @NotEmpty(message = "{blog.content.least.validation.constraints.NotNull.message}")
    private String content;
    @Builder.Default
    private String image = "resim.png";

    // Relations
    private BlogCategoryDto blogCategoryDto;
}
