package com.brunoam.CineLog.repositories;

import com.brunoam.CineLog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //
}
