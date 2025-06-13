package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Dto.Response.HienThiSanPhamRes;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Mapper.SanPhamChiTietMapper;
import datn.com.example.datn.sys.domain.Repository.SanPhamChiTietRepository;
import datn.com.example.datn.sys.domain.Service.SanPhamChiTietService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SanPhamChiTietServiceImpl implements SanPhamChiTietService {

    SanPhamChiTietRepository sanPhamChiTietRepository;
    SanPhamChiTietMapper sanPhamChiTietMapper;

    @Override
    public List<HienThiSanPhamRes> homeSanPham() {
        return sanPhamChiTietRepository.sanPham();
    }

    @Override
    public List<HienThiSanPhamRes> getSanPhamByIdDanhMuc(Integer idDanhMuc) {
        return sanPhamChiTietRepository.sanPhamByIdDanhMuc(idDanhMuc);
    }

    @Override
    public List<SanPhamChiTietRes> getSanPhamByIdSanPham(Integer idSanPham) {
        return sanPhamChiTietRepository.findSanPhamChiTietsByIdSanPham_Id(idSanPham).stream().map(sanPhamChiTietMapper::toSanPhamChiTietRes).toList();
    }

    @Override
    public List<HienThiSanPhamRes> themGioHan(Integer idSanPham) {
        return List.of();
    }

}
