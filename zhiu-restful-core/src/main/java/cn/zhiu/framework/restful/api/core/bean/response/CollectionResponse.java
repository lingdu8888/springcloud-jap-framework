package cn.zhiu.framework.restful.api.core.bean.response;


import cn.zhiu.framework.restful.api.core.bean.CommonBaseRestfulApiBean;

import java.util.List;

public class CollectionResponse<T> extends CommonBaseRestfulApiBean {

    private static final long serialVersionUID = 7801360002520756501L;

    public CollectionResponse(List<T> data) {
        this.data = data;
    }

    public CollectionResponse(List<T> data, String md5) {
        this.data = data;
        this.md5 = md5;
    }

    /**
     * 数据md5加密值
     */
    private String md5;

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
