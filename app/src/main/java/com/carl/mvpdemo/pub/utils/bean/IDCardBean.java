package com.carl.mvpdemo.pub.utils.bean;

import java.util.List;

/**
 * @author Carl
 *         version 1.0
 * @since 2019/7/20
 */
public class IDCardBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String idcard;
        private String name;

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
