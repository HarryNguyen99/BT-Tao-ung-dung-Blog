package controller;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BlogService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping()
    public ModelAndView showlistBlog(){
        List<Blog> bloglist = blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("bloglists", bloglist);
        return modelAndView;
    }

    @GetMapping("/blog/create")
    public ModelAndView creteBlog(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blogs", new Blog());
        return modelAndView;
    }

    @PostMapping("/blog/save")
    public ModelAndView saveBlog(@ModelAttribute("blog") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blogs", new Blog());
        modelAndView.addObject("success","Tạo Bài Blog Thành Công");
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView editBlog(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("blogs",blog);
            return modelAndView;
        }else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("edit")
    public ModelAndView updateBlog(@ModelAttribute("blogs") Blog blog){
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blogs",blog);
        modelAndView.addObject("success", "update Thành Công");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView ShowDeleteBlog(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null){
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("blogs", blog);
            return modelAndView;
        }else {
            return new ModelAndView("error.404");
        }
    }

    @PostMapping("delete")
    public ModelAndView deleteBlog(@ModelAttribute("blogs") Blog blog){
        ModelAndView modelAndView = new ModelAndView("delete");
        blogService.remove(blog.getId());
        modelAndView.addObject("success","Đã Xóa");
        return modelAndView;
    }

    @GetMapping("/blog/{id}")
    public ModelAndView view(@PathVariable Long id){
        ModelAndView modelAndView =new ModelAndView("view");
        modelAndView.addObject("blogs", blogService.findById(id));
        return modelAndView;
    }
}
