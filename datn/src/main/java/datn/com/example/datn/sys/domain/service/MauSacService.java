package datn.com.example.datn.sys.domain.service;

import datn.com.example.datn.sys.domain.entity.MauSac;

import java.util.List;

public interface MauSacService {
    List<MauSac> getAll();
    MauSac getById(Integer id);
    MauSac create(MauSac mauSac);
    MauSac update(Integer id, MauSac mauSac);
    boolean delete(Integer id);
}
