package com.springboot.security.service.impl;

import com.springboot.security.domain.Blog;
import com.springboot.security.service.IBlogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {
    private List<Blog> blogs = new ArrayList<>();


    public BlogServiceImpl() {
        blogs.add(new Blog(1L, "name1", "dddwdwdwdasdnfaklsjdnfjfnld"));
        blogs.add(new Blog(2L, "name2", "hello, I'm your sister blog"));
    }

    @Override
    public List<Blog> getBlogs() {
        return blogs;
    }

    @Override
    public void deleteBlog(Long id) {
        Iterator<Blog> blogIt = blogs.iterator();
        while( blogIt.hasNext()){
            Blog tmpBlog = blogIt.next() ;
            if( id == tmpBlog.getId()){
                blogIt.remove();
            }
        }
    }
}
