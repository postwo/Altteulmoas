package com.example.altteulmoa.service.image;

import com.example.altteulmoa.domain.entity.image.ImageEntity;
import com.example.altteulmoa.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgService {

    @Value(value = "${ImgLocation}")
    private String itemImgLocation;

    private final ImageRepository imageRepository;

    private final FileService fileService;

    public void saveItemImg(ImageEntity itemImg, MultipartFile itemImgFile) throws IOException {
        String originalFileName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(originalFileName)) {
            imgName = fileService.uploadFile(itemImgLocation, originalFileName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(originalFileName, imgName, imgUrl);
        imageRepository.save(itemImg);
    }

    public void updateItemImg(Long ItemImgId, MultipartFile itemImgFile) throws IOException {
        if(!itemImgFile.isEmpty()){
            ImageEntity itemImg = imageRepository.findById(ItemImgId).orElseThrow(EntityNotFoundException::new);

            // 파일 이름이 존재하는 경우
            if(!StringUtils.isEmpty(itemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());   // 기존 파일 삭제
            }

            String originalFileName = itemImgFile.getOriginalFilename();
            String imgNamae = fileService.uploadFile(itemImgLocation, originalFileName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgNamae;

            // 더티체킹
            itemImg.updateItemImg(originalFileName, imgNamae, imgUrl);  // 파일 정보 업데이트
        }
    }
}
