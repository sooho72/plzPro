package com.lyj.securitydomo.repository;


import com.lyj.securitydomo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE "
            + "(:keyword IS NULL OR p.title LIKE %:keyword% OR p.contentText LIKE %:keyword%) "
            + "AND (:types IS NULL OR p.title IN (:types))")
    Page<Post> searchAll(@Param("types") String[] types,
                         @Param("keyword") String keyword,
                         Pageable pageable);
}
