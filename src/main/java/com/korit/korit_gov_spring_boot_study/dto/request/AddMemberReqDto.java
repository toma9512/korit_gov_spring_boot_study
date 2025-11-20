package com.korit.korit_gov_spring_boot_study.dto.request;

import com.korit.korit_gov_spring_boot_study.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberReqDto {
    private String name;
    private Integer age;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .age(age)
                .build();
    }
}
