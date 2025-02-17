package org.springdemo.blog_backend.Service;


import org.springdemo.blog_backend.Repository.BlogRepository;
import org.springdemo.blog_backend.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogpostService {

    BlogRepository repository;





    public BlogpostService(BlogRepository repository) {
        this.repository = repository;
    }


    public void CreateNewPost(BlogPost post){

        repository.save(post);

    }

    public List<BlogPost> getPostByTitle(String title){
        return repository.findByTitle(title);
    }

    public List <BlogPost> getPostByAuthor(String author){
       return repository.findByAuthor(author);
    }

    public void deletePostByID(Long Id ){
        if(repository.existsById(Id)){
            repository.deleteById(Id);
        }else {
            throw new IllegalArgumentException("There is no post available such kind");
        }
    }

    public Optional<BlogPost> updatePost(Long Id, BlogPost updatedPost) {
        return repository.findById(Id).map(existingPost -> {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            return repository.save(existingPost);
        });

    }

}
