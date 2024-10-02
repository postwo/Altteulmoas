package com.example.altteulmoa.dto.request.find;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindEmailRequestDto {

    @NotBlank
    private String telNumber;

}
