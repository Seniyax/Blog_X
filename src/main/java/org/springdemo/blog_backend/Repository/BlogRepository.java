package org.springdemo.blog_backend.Repository;

import org.springdemo.blog_backend.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findByTitle(String title);
    List<BlogPost> findByAuthor(String author);


}


