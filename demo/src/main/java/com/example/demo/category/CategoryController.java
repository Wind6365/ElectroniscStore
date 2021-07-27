package com.example.demo.category;

import com.example.demo.query.CategoryQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(path = "get/categories/all")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping(path="get/categories/{categoryId}")
    public Optional<Category> getCategoryById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping(path="add/categories")
    public void addNewCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path="delete/categories/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping(path="update/categories/{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Long categoryId,
                               @RequestParam(required = false) String categoryName,
                               @RequestParam(required = false) Long parentId) {
        categoryService.updateCategory(categoryId, categoryName, parentId);
    }
}
