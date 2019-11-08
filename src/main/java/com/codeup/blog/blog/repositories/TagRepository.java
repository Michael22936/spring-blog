package com.codeup.blog.blog.repositories;

import com.codeup.blog.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
