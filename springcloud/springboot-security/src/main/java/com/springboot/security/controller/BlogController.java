package com.springboot.security.controller;

import com.springboot.security.domain.Blog;
import com.springboot.security.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private IBlogService service ;

    @GetMapping("/blogs")
    public ModelAndView getList(Model model){
        List<Blog> blogs = service.getBlogs();
        model.addAttribute("blogs", blogs);
        return new ModelAndView("blog/list", "blogModel", model);
    }

    @PreAuthorize("hasRole('USER')") // 执行前权限验证 spring security验证用于方法上，无论方法在哪个层次
    @GetMapping("/delete/{blogId}")
    public ModelAndView deleteBlog(@PathVariable("blogId") Long blogId, Model model){
        service.deleteBlog(blogId);
        model.addAttribute("blogs", service.getBlogs());
        return new ModelAndView("/blog/list", "blogModel", model);
    }
}
