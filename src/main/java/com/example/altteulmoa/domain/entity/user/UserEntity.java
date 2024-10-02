package com.example.altteulmoa.domain.entity.user;

import com.example.altteulmoa.domain.entity.BaseEntity;
import com.example.altteulmoa.domain.entity.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "email")
})
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {

    @Column(length = 100,nullable = false)
    private String email;

    @Column(length = 100,nullable = false)
    private String password;

    @Column(length = 100,nullable = false)
    private String nickname;

    @Lob
    private String profileImage;

    @Column(length = 100)
    private String address;

    @Column(nullable = false,length = 15)
    private String telNumber;;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;



    //회원가입
    public UserEntity(String email, String password, String nickname, UserStatus userStatus,String telNumber) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.userStatus = userStatus;
        this.telNumber =telNumber;
    }


    //로그인
    public UserEntity(String email,String password){
        this.email = email;
        this.password = password;
    }



    //주소 등록

}
