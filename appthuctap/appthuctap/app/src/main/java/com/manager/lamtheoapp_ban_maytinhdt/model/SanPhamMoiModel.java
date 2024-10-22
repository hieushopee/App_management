package com.manager.lamtheoapp_ban_maytinhdt.model;

import java.util.List;

public class SanPhamMoiModel {
    boolean success;
    String message;
    List<SanPhamMoi> result;

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

    public List<SanPhamMoi> getResult() {
        return result;
    }

    public void setResult(List<SanPhamMoi> result) {
        this.result = result;
    }
}
