package com.kbstar.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Cust {
    @Size(min = 4, max = 10, message = "ID 최소 4개 최대 10개 입니다.")
    @NotEmpty(message = "ID 필수 기재 사항입니다.")
    private String id;
    @Size(min = 8, max = 10, message = "PWD 최소 8자리 최대 20자리 입니다.")
    @NotEmpty(message = "PWD 필수 기재 사항입니다.")
    private String pwd;
    @NotEmpty(message = "NAME 필수 기재 사항입니다.")
    private String name;

}
