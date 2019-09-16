
package com.example.bloodbank.data.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private DataRegsiter dataRegsiter;

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

    public DataRegsiter getDataRegsiter() {
        return dataRegsiter;
    }

    public void setDataRegsiter(DataRegsiter dataRegsiter) {
        this.dataRegsiter = dataRegsiter;
    }

}
