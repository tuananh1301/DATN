package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.KichThuoc;

import java.util.List;

public interface KichThuocService {
    List<KichThuoc> getAll();
    KichThuoc getById(Integer id);
    KichThuoc create(KichThuoc kichThuoc);
    KichThuoc update(Integer id, KichThuoc kichThuoc);
    boolean delete(Integer id);
}
