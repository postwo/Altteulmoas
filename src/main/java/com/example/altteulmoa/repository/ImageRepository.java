package com.example.altteulmoa.repository;

import com.example.altteulmoa.domain.entity.image.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {


}
