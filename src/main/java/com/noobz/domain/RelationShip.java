package com.noobz.domain;

import java.io.Serializable;

public class RelationShip implements Serializable{
    private static final long serialVersionUID = 1L;

	private Integer cid;

    private Integer mid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}