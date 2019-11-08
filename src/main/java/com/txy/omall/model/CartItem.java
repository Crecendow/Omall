package com.txy.omall.model;

import javax.persistence.*;

@Table(name = "cart_item")
public class CartItem {
    @Id
    private Integer id;

    @Column(name = "goods_id")
    private String goodsId;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private String goodsPrice;

    private String amount;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return goods_id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * @return goods_name
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * @return goods_price
     */
    public String getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * @param goodsPrice
     */
    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice == null ? null : goodsPrice.trim();
    }

    /**
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }
}