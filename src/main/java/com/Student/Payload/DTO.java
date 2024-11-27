package com.Student.Payload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class DTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
}
