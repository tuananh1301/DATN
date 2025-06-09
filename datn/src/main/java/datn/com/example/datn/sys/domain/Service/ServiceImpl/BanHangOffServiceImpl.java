package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietReq;
import datn.com.example.datn.sys.domain.Dto.Request.SanPhamChiTietSellOffReq;
import datn.com.example.datn.sys.domain.Dto.Response.SanPhamChiTietRes;
import datn.com.example.datn.sys.domain.Entity.SanPhamChiTiet;
import datn.com.example.datn.sys.domain.Mapper.SanPhamChiTietMapper;
import datn.com.example.datn.sys.domain.Repository.SanPhamChiTietRepository;
import datn.com.example.datn.sys.domain.Service.BanHangOffService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BanHangOffServiceImpl implements BanHangOffService {
    SanPhamChiTietRepository sanPhamChiTietRepository;
    SanPhamChiTietMapper sanPhamChiTietMapper;
    @Override
    public List<SanPhamChiTietRes> findAllSanPhamChiTietsByMaSanPham(SanPhamChiTietSellOffReq sanPhamChiTietReq) {
        return sanPhamChiTietRepository.findSanPhamChiTietsByIdSanPham_MaSanPham(sanPhamChiTietReq.getIdKichThuoc(), sanPhamChiTietReq.getIdMauSac(), sanPhamChiTietReq.getMaSanPham())
                .stream().map(sanPhamChiTietMapper::toSanPhamChiTietRes).toList();
    }



}
