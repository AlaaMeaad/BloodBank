
package com.example.bloodbank.data.model.listGovern;

import java.util.List;

import com.example.bloodbank.data.model.listCity.GeneralModelData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Govern {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<GeneralModelData> data = null;

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

    public List<GeneralModelData> getData() {
        return data;
    }

    public void setData(List<GeneralModelData> data) {
        this.data = data;
    }

}
