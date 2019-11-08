package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
