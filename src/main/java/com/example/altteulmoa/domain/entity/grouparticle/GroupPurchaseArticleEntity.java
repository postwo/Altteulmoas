package com.example.altteulmoa.domain.entity.grouparticle;


import com.example.altteulmoa.domain.AuditingFields;
import com.example.altteulmoa.domain.entity.grouparticle.enums.ArticleStatus;
import com.example.altteulmoa.domain.entity.image.ImageEntity;
import com.example.altteulmoa.domain.entity.user.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(columnList = "title")
})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class GroupPurchaseArticleEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100,nullable = false)
    private String title;

    @Lob
    private String content;

    @Column(nullable = false)
    @Min(1) // 최소 1명
    @Max(4) // 최대 4명
    private int participantCount; //공동구매 인원수

    @Column(nullable = false)
    @NotNull(message = "가격은 필수입니다.")
    private double price;

    @Column(nullable = false)
    private String address;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleStatus articleStatus;

    // 작성자 정보 (주소 포함)
    @ManyToOne(fetch = FetchType.LAZY)  // 사용자 정보는 다대일 관계
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // 이미지 정보 (일대다 관계)
    @OneToMany(mappedBy = "groupPurchaseArticle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();
}
