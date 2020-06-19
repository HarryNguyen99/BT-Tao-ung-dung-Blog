package com.spring.controller;

import com.spring.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.spring.service.BlogService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/blog")
public class BlogControllerAPI {

    @Autowired
    private BlogService blogService;


    @GetMapping("/lists")
    public ResponseEntity<Page<Blog>> listBlog(Pageable pageable){
        Page<Blog> blogs = blogService.findAll(pageable);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Blog>> searchBlog(@RequestParam("author") String author) {
        List<Blog> blogList = new LinkedList<>();
        //TO DO: Code de lay blogList
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(blogList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView listBlogs( Pageable pageable){
        ModelAndView modelAndView =new ModelAndView("blog/list");
        modelAndView.addObject("blogLists", blogService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBlog(){
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @PostMapping("/creates")
    public ResponseEntity<Blog> createBlogs(@RequestBody Blog blog){
        blogService.save(blog);
        return new ResponseEntity<>(blog, HttpStatus.OK);
    }

//    @GetMapping("delete/{id}")
//    public ModelAndView ShowDeleteBlog(@PathVariable Long id){
//            ModelAndView modelAndView = new ModelAndView("delete");
//            modelAndView.addObject("blogs", blogService.findById(id));
//            return modelAndView;
//    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Blog> deleteBlog(@PathVariable Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (blog != null){
            blogService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editBlog(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        Optional<Blog> blog = blogService.findById(id);
        modelAndView.addObject("blogs",blog);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Blog> editBlogs(@PathVariable Long id, @RequestBody Blog blog){
        Optional<Blog> blog1 = blogService.findById(id);
        if (blog1 != null){
            blog.setId(id);
            blogService.save(blog);
            return  new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
