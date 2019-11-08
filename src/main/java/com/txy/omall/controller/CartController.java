package com.txy.omall.controller;

import com.txy.omall.model.Cart;
import com.txy.omall.model.CartItem;
import com.txy.omall.service.ICart;
import com.txy.omall.service.IGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    private static final String SUCCESS_CODE = "200";
    private static final String FAIL_CODE = "500";

    @RequestMapping("/page")
    public String getCartPage(){
        return  "cart.html";
    }

    @Autowired
    private ICart cartService;

    @Autowired
    private IGoods goodsService;

    @GetMapping("/list")
    @ResponseBody
    public List<CartItem> getCartList(){
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList = cartService.getCartItemList();
        return cartItemList;
    }

    @GetMapping("/deleteCartItem")
    @ResponseBody
    public List<CartItem> deleteCartItem(@Param("id") String id ){
        int inputId = Integer.parseInt(id);
        if(cartService.deleteCartItem(inputId)){
            List<CartItem> cartItemList = new ArrayList<>();
            cartItemList = cartService.getCartItemList();
            return cartItemList;
        }else{
            return null;
        }
    }

    @PostMapping("/updateCartItem")
    @ResponseBody
    public String updateCartItem(@Param("amount")String amount,@Param("goodsId")String goodsId){
        boolean updateResult = goodsService.addAmount(goodsId,amount);
        if(updateResult){
            return SUCCESS_CODE;
        }else{
            return FAIL_CODE;
        }
    }
}
