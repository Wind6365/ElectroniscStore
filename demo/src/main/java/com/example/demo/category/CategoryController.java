package com.example.demo.category;

import com.example.demo.query.CategoryQueryResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping(path = "get/categories/all")
    @ApiOperation(value = "Get the List of Categories")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping(path="get/categories/{categoryId}")
    @ApiOperation(value = "Get category by id")
    public Optional<Category> getCategoryById(@PathVariable("categoryId") Long categoryId){
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping(path="add/categories")
    @ApiOperation(value = "Add category")
    public void addNewCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path="delete/categories/{categoryId}")
    @ApiOperation(value = "Delete category by id")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping(path="update/categories/{categoryId}")
    @ApiOperation(value = "Update category by id")
    public void updateCategory(@PathVariable("categoryId") Long categoryId,
                               @RequestParam(required = false) String categoryName,
                               @RequestParam(required = false) Long parentId) {
        categoryService.updateCategory(categoryId, categoryName, parentId);
    }
}
