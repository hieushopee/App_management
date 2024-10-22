package com.manager.lamtheoapp_ban_maytinhdt.model;

import java.util.List;

public class LoaiSpModel {
    boolean success;
    String message;
    List<LoaiSp> result;

    public boolean isSucess() {
        return success;
    }

    public void setSucess(boolean sucess) {
        this.success = sucess;
    }

    public String getMessege() {
        return message;
    }

    public void setMessege(String messege) {
        this.message = messege;
    }

    public List<LoaiSp> getResult() {
        return result;
    }

    public void setResult(List<LoaiSp> result) {
        this.result = result;
    }
}
