package com.example.altteulmoa.service.grouppurchasearticle;

import com.example.altteulmoa.converter.grouparticle.GroupPurchaseArticleConverter;
import com.example.altteulmoa.domain.entity.grouparticle.GroupPurchaseArticleEntity;
import com.example.altteulmoa.domain.entity.image.ImageEntity;
import com.example.altteulmoa.domain.entity.user.UserEntity;
import com.example.altteulmoa.dto.request.article.GroupPurchaseArticleRequestDto;
import com.example.altteulmoa.dto.response.article.GroupPurchaseArticleResponseDto;
import com.example.altteulmoa.repository.GroupPurchaseArticleRepository;
import com.example.altteulmoa.repository.UserRepository;
import com.example.altteulmoa.service.image.ImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupPurchaseArticleService {

    private final GroupPurchaseArticleRepository groupPurchaseArticleRepository;

    private final UserRepository userRepository;

    private final ImgService imgService;


    public ResponseEntity<? super GroupPurchaseArticleResponseDto> articleSave(GroupPurchaseArticleRequestDto articleRequestDto, List<MultipartFile> itemImgFileList) throws IOException {

        // 사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 사용자입니다."));

        // 게시글 생성
        GroupPurchaseArticleEntity article = GroupPurchaseArticleConverter.toEntity(articleRequestDto, user);
        groupPurchaseArticleRepository.save(article);

        // 이미지 저장
        for (MultipartFile itemImgFile : itemImgFileList) {
            ImageEntity itemImg = new ImageEntity();
            itemImg.setGroupPurchaseArticle(article);
            imgService.saveItemImg(itemImg, itemImgFile);
        }

        return GroupPurchaseArticleResponseDto.success();
    }



}
