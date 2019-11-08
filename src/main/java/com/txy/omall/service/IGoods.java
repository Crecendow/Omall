package com.txy.omall.service;

import com.txy.omall.model.CartItem;
import com.txy.omall.model.Goods;
import java.util.List;


public interface IGoods {
    public List<Goods> getGoodsAll();
    public boolean addToCart(String goodsId , String goodsName , String goodsPrice , String amount);
    public CartItem getCartItemById(String goodsId);
    public boolean addAmount( String goodsId ,String newAmount);
}
