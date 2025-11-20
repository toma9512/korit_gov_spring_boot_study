package com.korit.korit_gov_spring_boot_study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePostReqDto {
    private Integer postId;
    private String title;
    private String content;
}
