package com.springboot.security.service;

import com.springboot.security.domain.Blog;

import java.util.List;

public interface IBlogService {
    List<Blog> getBlogs();

    void deleteBlog(Long id );
}
