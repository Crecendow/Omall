package com.txy.omall.dao;

import com.txy.omall.model.CartItem;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.MyMapper;

public interface CartItemMapper extends MyMapper<CartItem> {
    public int deleteCartItem(int id);

    public int updateCartItem(@Param( "cartItem" ) CartItem cartItem);
}
