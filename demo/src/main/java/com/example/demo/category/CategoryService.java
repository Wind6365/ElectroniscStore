package com.example.demo.category;

import com.example.demo.good.Good;
import com.example.demo.good.GoodRepository;
import com.example.demo.query.CategoryQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final GoodRepository goodRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, GoodRepository goodRepository){
        this.categoryRepository = categoryRepository;
        this.goodRepository = goodRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public void addNewCategory(Category category) {
        Optional<Category> categoryByCategoryName = categoryRepository.findCategoryByCategoryName(category.getCategoryName());
        if(categoryByCategoryName.isPresent()){
            throw new IllegalStateException("Category already exists");
        }
        if(category.getParent() != null) {
            Optional<Category> parentCategory = categoryRepository.findById(category.getParent().getId());
            parentCategory.ifPresent(category::setParent);
        }
        categoryRepository.save(category);
        System.out.println(category);
    }

    public void deleteCategory(Long categoryId) {
        boolean categoryExists = categoryRepository.existsById(categoryId);
        if(!categoryExists) {
            throw new IllegalStateException("Category with id " + categoryId + " does not exist");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void updateCategory(Long categoryId, String categoryName, Long parentId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new IllegalStateException("Category with id " + categoryId + " does not exist"));

        if(categoryName != null && categoryName.length() > 0 && !Objects.equals(category.getCategoryName(), categoryName)){
            Optional<Category> categoryByName = categoryRepository.findCategoryByCategoryName(categoryName);
            if(categoryByName.isPresent()){
                throw new IllegalStateException("The Category with the " + categoryName + " already exists");
            }
            category.setCategoryName(categoryName);
        }

        if(parentId!= null && categoryRepository.findById(parentId).isPresent()){
            category.setParent(categoryRepository.getById(parentId));
        }
    }
}
