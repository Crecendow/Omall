package com.txy.omall.controller;


import com.txy.omall.model.CartItem;
import com.txy.omall.model.Goods;
import com.txy.omall.service.IGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Goods")
public class GoodsController {
    private final static String SUCCESS_CODE = "200";
    private final static String FAIL_CODE ="500";

    @Autowired
    private IGoods goodsService;

    @GetMapping("/list")
    @ResponseBody
    public List<Goods> getGoodsAll(){
        Map<String,Object> map = new HashMap<String,Object>();
        List<Goods> goodsList = goodsService.getGoodsAll();
        if(goodsList != null){
          map.put("data",goodsList);
        }
       return goodsList;
    }

    @PostMapping("/addToCart")
    @ResponseBody
    public String addToCart(@Param("goodsId") String goodsId ,@Param("goodsName") String goodsName ,@Param("goodsPrice") String goodsPrice,@Param("amount")String amount){
        int inputAmount = Integer.parseInt(amount);
        CartItem cartItem = new CartItem();
        cartItem = goodsService.getCartItemById(goodsId);
        boolean insertResult;
        if(cartItem == null){
            insertResult =  goodsService.addToCart(goodsId,goodsName,goodsPrice,amount);
        }else{
            int oldAmount = Integer.parseInt(cartItem.getAmount());
            int newAmount = inputAmount+oldAmount;
            String newAmountStr =String.valueOf(newAmount);
            insertResult = goodsService.addAmount( goodsId ,newAmountStr);
        }
        if (insertResult){
            return  SUCCESS_CODE;
        }else {
            return FAIL_CODE;
        }
    }
}
