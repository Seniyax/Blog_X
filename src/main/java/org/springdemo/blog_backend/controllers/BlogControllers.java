package org.springdemo.blog_backend.controllers;

import org.springdemo.blog_backend.Service.BlogpostService;
import org.springdemo.blog_backend.model.BlogPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blog")
public class BlogControllers {

    BlogpostService blogpostService;

    public BlogControllers(BlogpostService blogpostService) {
        this.blogpostService = blogpostService;
    }

    @PostMapping
    public ResponseEntity <BlogPost> CreatePost(@RequestBody BlogPost post) {
        blogpostService.CreateNewPost(post);

        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BlogPost>> GetPostByTitle( @RequestParam String title) {
      List<BlogPost> posts = blogpostService.getPostByTitle(title);

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/search/author")
    public ResponseEntity <List<BlogPost>> GetPostByAuthor(@RequestParam String author) {
        List <BlogPost> posts = blogpostService.getPostByAuthor(author);

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public  ResponseEntity <String> deletePost(@PathVariable Long Id) {
        try{
            blogpostService.deletePostByID(Id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{Id}")
    public ResponseEntity <BlogPost> updatePost(@PathVariable Long Id, @RequestBody BlogPost post) {
        try {
            blogpostService.updatePost(Id,post);
            return new ResponseEntity<>(post,HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
