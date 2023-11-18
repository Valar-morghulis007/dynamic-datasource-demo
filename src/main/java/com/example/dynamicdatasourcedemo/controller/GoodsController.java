package com.example.dynamicdatasourcedemo.controller;

import com.example.dynamicdatasourcedemo.domain.Goods;
import com.example.dynamicdatasourcedemo.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wls
 */
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    /**
     * 保存
     * @param goods 商品
     * @return  是否成功
     */
    @PostMapping("/saveGoods")
    public boolean saveGoods(@RequestBody Goods goods){
        return goodsService.saveGoods(goods);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @DeleteMapping("/deleteGoods")
    public boolean deleteGoods(Long id) {
        return goodsService.deleteGoods(id);
    }

    /**
     * 查询全部
     *
     * @return 全部
     */
    @GetMapping("/getGoodsAll")
    public List<Goods> getGoodsAll() {
        List<Goods> goodsAll = goodsService.getGoodsAll();
        return goodsAll;
    }

    /**
     * 查询单个
     *
     * @param id id
     * @return 商品
     */
    @GetMapping("getGoodsById")
    public Goods getGoodsById(Long id) {
        return goodsService.getGoodsById(id);
    }
}
