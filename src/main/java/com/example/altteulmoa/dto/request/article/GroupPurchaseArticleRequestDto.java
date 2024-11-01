package com.example.altteulmoa.dto.request.article;

import com.example.altteulmoa.domain.entity.grouparticle.enums.ArticleStatus;
import com.example.altteulmoa.dto.request.image.ImageRequestDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupPurchaseArticleRequestDto {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @Min(1) // 최소 1명
    @Max(4) // 최대 4명
    private int participantCount; // 공동구매 인원수

    @NotNull(message = "가격은 필수입니다.")
    private double price;

    private String address;

    private ArticleStatus articleStatus;

    private List<ImageRequestDto> image;



}
