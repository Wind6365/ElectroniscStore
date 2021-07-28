package com.example.demo.category;

import com.example.demo.good.Good;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Category")
@Table(name = "category")
public class Category {
    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "category_sequence"
    )
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    @ApiModelProperty(notes = "The unique id of the category", readOnly = true)
    private long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private List<Good> goods;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private List<Category> childCategories;

    @Column(
            name = "category_name",
            nullable = false
    )
    @ApiModelProperty(notes = "The name of the category")
    private String categoryName;

    @ManyToOne
    private Category parent;

    public Category() {
    }

    public Category(String categoryName, Category parent) {
        this.categoryName = categoryName;
        this.parent = parent;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }

    public long getId() {
        return id;
    }

    public void setId(int category_id) {
        this.id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent_id) {
        this.parent = parent_id;
    }

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + id +
                ", category_name='" + categoryName + '\'' +
                '}';
    }
}
