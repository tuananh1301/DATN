package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.HoaDon;

import java.util.List;

public interface HoaDonService {
    List<HoaDon> getAll();
    HoaDon getById(Integer id);
    HoaDon create(HoaDon hoaDon);
    HoaDon update(Integer id, HoaDon hoaDon);
    boolean delete(Integer id); // chỉ xóa nếu trạng thái là "pending"
}
