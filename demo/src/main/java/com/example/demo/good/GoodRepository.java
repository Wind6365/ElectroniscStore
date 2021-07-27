package com.example.demo.good;

import com.example.demo.category.Category;
import com.example.demo.query.GoodQueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {
    @Query("SELECT g FROM Good g WHERE g.goodName = ?1")
    Optional<Good> findGoodByGoodName(String name);

    @Query("SELECT new com.example.demo.query.GoodQueryResult(g.goodId, g.goodName, g.goodPrice, c.id) " +
            "FROM Good g, Category c " +
            "WHERE g.category.id = c.id")
    List<GoodQueryResult> getGoodsWithCategoryId();

    @Query("SELECT new com.example.demo.query.GoodQueryResult(g.goodId, g.goodName, g.goodPrice, c.id) " +
            "FROM Good g, Category c " +
            "WHERE g.category.id = c.id AND g.goodId = ?1")
    Optional<GoodQueryResult> getGoodWithCategoryId(Long goodId);

    List<Good> findGoodByCategoryId(Long categoryId);
}
