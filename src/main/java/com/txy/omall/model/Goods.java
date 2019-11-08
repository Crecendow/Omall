package com.txy.omall.model;

import javax.persistence.*;

public class Goods {
    @Id
    @Column(name = "goods_id")
    private Integer goodsId;

    private String type;

    @Column(name = "goods_name")
    private String goodsName;

    @Column(name = "goods_price")
    private String goodsPrice;

    /**
     * @return goods_id
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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
}