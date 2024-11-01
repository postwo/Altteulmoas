package com.example.altteulmoa.domain.entity.image;

import com.example.altteulmoa.domain.AuditingFields;
import com.example.altteulmoa.domain.entity.grouparticle.GroupPurchaseArticleEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ImageEntity extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgName;  // 저장된 이미지 파일명

    @Column(nullable = false)
    private String oriImgName; // 원본 이미지 파일명

    @Column(nullable = false)
    private String imgUrl;          // 이미지 저장 경로

    @Column(nullable = false)
    private int repImgYn;        // 대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupPurchaseArticle_id")
    private GroupPurchaseArticleEntity groupPurchaseArticle;

    /**
     * 상품 이미지 수정 - 더티체킹
     * @param oriImgName
     * @param imgName
     * @param imgUrl
     */
    public void updateItemImg(String oriImgName, String imgName,  String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

}
