package com.manager.lamtheoapp_ban_maytinhdt.model.EvenBus;

import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;

public class SuaXoa {
    SanPhamMoi sanPhamMoi;

    public SuaXoa(SanPhamMoi sanPhamMoi) {
        this.sanPhamMoi = sanPhamMoi;
    }

    public SanPhamMoi getSanPhamMoi() {
        return sanPhamMoi;
    }

    public void setSanPhamMoi(SanPhamMoi sanPhamMoi) {
        this.sanPhamMoi = sanPhamMoi;
    }
}
