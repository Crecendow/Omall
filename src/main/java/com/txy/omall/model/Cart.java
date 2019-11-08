package com.txy.omall.model;

import javax.persistence.*;

public class Cart {
    @Id
    private Integer id;

    private Integer cid;

    private String tprice;

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
     * @return cid
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * @param cid
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * @return tprice
     */
    public String getTprice() {
        return tprice;
    }

    /**
     * @param tprice
     */
    public void setTprice(String tprice) {
        this.tprice = tprice == null ? null : tprice.trim();
    }
}