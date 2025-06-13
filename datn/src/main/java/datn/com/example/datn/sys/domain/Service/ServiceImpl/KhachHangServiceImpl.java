package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Dto.Response.KhachHangRes;
import datn.com.example.datn.sys.domain.Mapper.KhachHangMapper;
import datn.com.example.datn.sys.domain.Repository.KhachHangRepository;
import datn.com.example.datn.sys.domain.Service.KhachHangService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangServiceImpl implements KhachHangService {
    KhachHangRepository khachHangRepository;
    KhachHangMapper khachHangMapper;

    @Override
    public KhachHangRes searchKhachHang(String soDienThoai) {
        return khachHangMapper.toKhachHang(khachHangRepository.findBySoDienThoai(soDienThoai));
    }
}
