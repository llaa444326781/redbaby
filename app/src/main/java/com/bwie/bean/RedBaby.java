package com.bwie.bean;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class RedBaby {
    private String message;
    private int status;
    private List<RsBean> rs;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RsBean> getRs() {
        return rs;
    }

    public void setRs(List<RsBean> rs) {
        this.rs = rs;
    }


}
