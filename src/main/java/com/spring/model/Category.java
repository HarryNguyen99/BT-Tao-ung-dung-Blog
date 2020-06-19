package com.spring.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;

//    @OneToMany(targetEntity = Blog.class)
//    private List<Blog> bloglist;

    public Category() {
    }

    public Category(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
        ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

//    public List<Blog> getBloglist() {
//        return bloglist;
//    }
//
//    public void setBloglist(List<Blog> bloglist) {
//        this.bloglist = bloglist;
//    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
