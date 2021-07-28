package com.example.demo.good;

import com.example.demo.category.Category;
import com.example.demo.category.CategoryRepository;
import com.example.demo.query.GoodQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GoodService {

    private final GoodRepository goodRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository, CategoryRepository categoryRepository){
        this.goodRepository = goodRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<?> getGoods(){
        return goodRepository.getGoodsWithCategoryId();
    }

    public Optional<GoodQueryResult> getGoodById(Long id){
        return goodRepository.getGoodWithCategoryId(id);
    }

    public void addNewGood(Good good) {
        Optional<Good> goodByName = goodRepository.findGoodByGoodName(good.getGoodName());
        if(goodByName.isPresent()){
            throw new IllegalStateException("The good already exists");
        }
        Optional<Category> category = categoryRepository.findById(good.getCategory().getId());
        category.ifPresent(good::setCategory);
        goodRepository.save(good);
    }

    public void deleteGood(Long goodId) {
        boolean goodExists = goodRepository.existsById(goodId);
        if(!goodExists){
            throw new IllegalStateException("Good with id " + goodId + " does not exist");
        }
        goodRepository.deleteById(goodId);
    }

    @Transactional
    public void updateGood(Long goodId, String goodName, Long categoryId, Double goodPrice) {
         Good good = goodRepository.findById(goodId).orElseThrow(() ->
                 new IllegalStateException("Good with id " + goodId + " does not exist"));

         if(goodName != null && goodName.length() > 0 && !Objects.equals(good.getGoodName(), goodName)){
             Optional<Good> goodByName = goodRepository.findGoodByGoodName(goodName);
             if(goodByName.isPresent()){
                 throw new IllegalStateException("The good with the " + goodName + " already exists");
             }
             good.setGoodName(goodName);
         }

         if(goodPrice != null && !Objects.equals(good.getGoodPrice(), goodPrice)){
            good.setGoodPrice(goodPrice);
         }

        System.out.println("======================================================================");
        System.out.println("======================================================================");
        System.out.println("======================================================================");

        if(categoryId!= null && categoryRepository.findById(categoryId).isPresent())
            good.setCategory(categoryRepository.getById(categoryId));
    }
}
