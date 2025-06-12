package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Repository.HoaDonChiTietRepository;
import datn.com.example.datn.sys.domain.Service.HoaDonChiTietService;
import org.springframework.stereotype.Service;

@Service
public class HoaDonChiTietServiceImpl implements HoaDonChiTietService {
    HoaDonChiTietRepository hoaDonChiTietRepository;
    @Override
    public void delete(Integer idHoaDonChiTiet) {
        hoaDonChiTietRepository.deleteById(idHoaDonChiTiet);
    }
}
