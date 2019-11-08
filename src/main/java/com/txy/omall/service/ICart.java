package com.txy.omall.service;

import com.txy.omall.model.Cart;
import com.txy.omall.model.CartItem;
import com.txy.omall.model.Goods;
import java.util.List;

public interface ICart  {
    public List<CartItem> getCartItemList();
    public boolean deleteCartItem(int id);

}
