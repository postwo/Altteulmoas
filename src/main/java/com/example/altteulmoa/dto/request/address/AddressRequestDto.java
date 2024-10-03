package com.example.altteulmoa.dto.request.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressRequestDto {

    @NotBlank
    private String address;

}