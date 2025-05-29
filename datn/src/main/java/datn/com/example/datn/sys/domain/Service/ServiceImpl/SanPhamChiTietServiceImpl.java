package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Dto.HienThiSanPhamDto;
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

    @Override
    public List<HienThiSanPhamDto> homeSanPham() {
        return sanPhamChiTietRepository.sanPham();
    }

    @Override
    public List<HienThiSanPhamDto> getSanPhamByIdDanhMuc(Integer idDanhMuc) {
        return sanPhamChiTietRepository.sanPhamByIdDanhMuc(idDanhMuc);
    }

    @Override
    public List<HienThiSanPhamDto> getSanPhamByIdSanPham(Integer idSanPham) {
        return List.of();
    }

}
