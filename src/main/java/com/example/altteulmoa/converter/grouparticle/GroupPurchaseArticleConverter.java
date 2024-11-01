package com.example.altteulmoa.converter.grouparticle;

import com.example.altteulmoa.domain.entity.grouparticle.GroupPurchaseArticleEntity;
import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.request.article.GroupPurchaseArticleRequestDto;

public class GroupPurchaseArticleConverter {


    //게시글
    public static GroupPurchaseArticleEntity toEntity(GroupPurchaseArticleRequestDto dto, UserEntity user){
        return GroupPurchaseArticleEntity.builder()
                .price(dto.getPrice())
                .title(dto.getTitle())
                .articleStatus(dto.getArticleStatus())
                .address(dto.getAddress())
                .participantCount(dto.getParticipantCount())
                .user(user)
                .build();
    }

}
