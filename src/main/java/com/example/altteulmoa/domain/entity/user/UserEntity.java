package com.example.altteulmoa.domain.entity.user;

import com.example.altteulmoa.domain.AuditingFields;
import com.example.altteulmoa.domain.entity.grouparticle.GroupPurchaseArticleEntity;
import com.example.altteulmoa.domain.entity.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(columnList = "email")
})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    // 사용자가 등록한 공동구매 글 리스트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GroupPurchaseArticleEntity> purchases;

}
