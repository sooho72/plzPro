package com.lyj.securitydomo.repository;


import com.lyj.securitydomo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
