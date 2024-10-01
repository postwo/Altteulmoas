package com.example.altteulmoa.dto.request.auth;

import com.example.altteulmoa.domain.entity.user.enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto { // 프론트에서 받아오는 것들

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
//    @Pattern(regexp = "^[0-9]{11,13}$")
    private String nickname;

    private UserStatus userStatus;

}