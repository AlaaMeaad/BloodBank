
package com.example.bloodbank.data.model.allposts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllPosts {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private PostPagnatoin data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PostPagnatoin getData() {
        return data;
    }

    public void setData(PostPagnatoin data) {
        this.data = data;
    }
}
