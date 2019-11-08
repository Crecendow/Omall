package com.txy.omall.serviceImpl;

import com.sun.org.apache.xpath.internal.operations.And;
import com.txy.omall.dao.CartItemMapper;
import com.txy.omall.dao.GoodsMapper;
import com.txy.omall.model.CartItem;
import com.txy.omall.model.Goods;
import com.txy.omall.service.IGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

@Service
public class GoodsImpl implements IGoods {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Override
    public List<Goods> getGoodsAll(){
        List<Goods> goodsList = goodsMapper.selectAll();
        return  goodsList;
    }

    @Override
    public  boolean  addToCart(String goodsId , String goodsName , String goodsPrice , String amount){
        CartItem cartItem = new CartItem();
        cartItem.setGoodsId(goodsId);
        cartItem.setGoodsName(goodsName);
        cartItem.setGoodsPrice(goodsPrice);
        cartItem.setAmount(amount);
        int insertResult =  cartItemMapper.insertSelective(cartItem);
        if(insertResult > 0 ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public CartItem getCartItemById(String goodsId){
        Example example = new Example(CartItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodsId",goodsId);
        CartItem cartItem= cartItemMapper.selectOneByExample(example);
        return cartItem;
    }

    @Override
     public boolean addAmount(String goodsId ,String newAmount){
        CartItem cartItem = new CartItem();
        cartItem.setGoodsId( goodsId );
        cartItem.setAmount( newAmount );
        int addStatus = cartItemMapper.updateCartItem(cartItem);
        if(addStatus > 0){
            return true;
        }else{
            return false;
        }
    }
}
