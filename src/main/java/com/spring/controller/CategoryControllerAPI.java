package com.spring.controller;

import com.spring.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.spring.service.CategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryControllerAPI {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("lists")
    public ResponseEntity<Page<Category>> listCategorys(Pageable pageable){
        Page<Category> categorylist = categoryService.findAll(pageable);
        return new ResponseEntity<>(categorylist, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ModelAndView listCategory(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categoryLists", categoryService.findAll(pageable));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCategory(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("categorys", new Category());
        return modelAndView;
    }

    @PostMapping("/creates")
    public ResponseEntity<Category> createCategorys(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (category != null){
            categoryService.remove(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editCategory(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("category/edit");
        Optional<Category> category = categoryService.findById(id);
        modelAndView.addObject("categorys",category);
        return modelAndView;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> editBlogs(@PathVariable Long id, @RequestBody Category category){
        Optional<Category> category1 = categoryService.findById(id);
        if (category1 != null){
            category.setId(id);
            categoryService.save(category);
            return  new ResponseEntity<>(category, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
