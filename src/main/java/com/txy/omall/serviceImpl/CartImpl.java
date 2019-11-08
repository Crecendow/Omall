package com.txy.omall.serviceImpl;


import com.txy.omall.dao.CartItemMapper;
import com.txy.omall.model.Cart;
import com.txy.omall.model.CartItem;
import com.txy.omall.model.User;
import com.txy.omall.service.ICart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;



@Service
public class CartImpl implements ICart
{
    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<CartItem> getCartItemList(){
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList = cartItemMapper.selectAll();
        return  cartItemList;
    }

    @Override
    public boolean deleteCartItem(int id){
        int s =  cartItemMapper.deleteCartItem(id);
        if(s>0){
            return true;
        }else {
            return false;
        }
    }


}
