package com.example.altteulmoa.controller;

import com.example.altteulmoa.dto.request.article.GroupPurchaseArticleRequestDto;
import com.example.altteulmoa.dto.response.article.GroupPurchaseArticleResponseDto;
import com.example.altteulmoa.service.grouppurchasearticle.GroupPurchaseArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
@Slf4j
public class GroupPurchaseArticleController {

    private final GroupPurchaseArticleService groupPurchaseArticleService;


    @PostMapping("/article")
    public ResponseEntity<? super GroupPurchaseArticleResponseDto> articlesave(GroupPurchaseArticleRequestDto articleRequestDto,
                                                                                 @RequestParam("itemImgFileList") List<MultipartFile> itemImgFileList) throws IOException {
        // 현재 인증 정보 확인 로그
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("현재 인증된 사용자: " + (authentication != null ? authentication.getName() : "없음"));

//        if (customUserDetails == null) {
//            log.error("customUserDetails가 null입니다. 인증되지 않은 사용자입니다.");
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }

        log.info("=====> 여기야");
        ResponseEntity<? super GroupPurchaseArticleResponseDto> response = groupPurchaseArticleService.articleSave(articleRequestDto, itemImgFileList);
        return response;
    }


}
