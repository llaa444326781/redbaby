package com.bwie.bean;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/14.
 */
public class Bean {

    public String api;
    public String code;
    public String msg;
    public String v;
    public int version;

    public List<DataBean> data;

    public class DataBean {
        public int elementShowNumber;
        public int elementType;
        public String modelFullCode;
        public int modelFullId;
        public int modelId;
        public int pmodelFullId;
        public int sequence;

        public List<TagBean> tag;


        public class TagBean {
            public String businessType;
            public String color;
            public String elementDesc;
            public String elementName;
            public int elementType;
            public int linkType;
            public String linkUrl;
            public int modelFullId;
            public String picUrl;
            public String productSpecialFlag;
            public int sequence;
            public int templateFullId;
            public String trickPoint;

        }
    }
}
