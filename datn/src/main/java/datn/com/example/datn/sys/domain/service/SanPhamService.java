package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.SanPham;

import java.util.List;

public interface SanPhamService {
    List<SanPham> getAll();
    SanPham getById(Integer id);
    SanPham create(SanPham sanPham);
    SanPham update(Integer id, SanPham sanPham);
    boolean delete(Integer id);

}
