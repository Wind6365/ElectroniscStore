package com.example.demo.category;

import com.example.demo.query.CategoryQueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.categoryName = ?1")
    Optional<Category> findCategoryByCategoryName(String name);

    @Query("SELECT new com.example.demo.query.CategoryQueryResult(c.id, c.categoryName, c.parent.id, c.parent.categoryName," +
            "g.goodId, g.goodName) " +
            "FROM Good g, Category c " +
            "WHERE g.category.id = c.id")
    List<CategoryQueryResult> getCategoryAndGoods();
}
