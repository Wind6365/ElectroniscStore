package com.example.demo.good;

import com.example.demo.query.GoodQueryResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api")
public class GoodController {

    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService){
        this.goodService = goodService;
    }

    @GetMapping(path = "get/goods/all")
    @ApiOperation(value = "Get List of Goods")
    public List<?> getGoods(){
        return goodService.getGoods();
    }

    @GetMapping(path = "get/goods/{goodId}")
    @ApiOperation(value = "Get good by id")
    public Optional<GoodQueryResult> getGoodById(@PathVariable("goodId") Long goodId){
        return goodService.getGoodById(goodId);
    }

    @PostMapping(path="add/goods")
    @ApiOperation(value = "Add good")
    public void addNewGood(@RequestBody Good good){
        goodService.addNewGood(good);
    }

    @DeleteMapping(path="delete/goods/{goodId}")
    @ApiOperation(value = "Delete good by id")
    public void deleteGood(@PathVariable("goodId") Long goodId){
        goodService.deleteGood(goodId);
    }

    @PutMapping(path = "update/goods/{goodId}")
    @ApiOperation(value = "Update good by id")
    public void updateGood(@PathVariable("goodId") Long goodId,
                           @RequestParam(required = false) String goodName,
                           @RequestParam(required = false) Long categoryId,
                           @RequestParam(required = false) Double goodPrice) {
        goodService.updateGood(goodId, goodName, categoryId, goodPrice);
    }
}
