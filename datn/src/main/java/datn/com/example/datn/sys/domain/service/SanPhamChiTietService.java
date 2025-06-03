package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.SanPhamChiTiet;

import java.util.List;

public interface SanPhamChiTietService {
    List<SanPhamChiTiet> getAll();
    SanPhamChiTiet getById(Integer id);
    SanPhamChiTiet create(SanPhamChiTiet spct);
    SanPhamChiTiet update(Integer id, SanPhamChiTiet spct);
    void delete(Integer id);
}
