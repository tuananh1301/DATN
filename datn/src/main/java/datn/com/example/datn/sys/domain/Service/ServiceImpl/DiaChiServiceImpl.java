package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.sys.domain.Dto.Request.DiaChiReq;
import datn.com.example.datn.sys.domain.Entity.DiaChi;
import datn.com.example.datn.sys.domain.Mapper.DiaChiMapper;
import datn.com.example.datn.sys.domain.Repository.DiaChiRepository;
import datn.com.example.datn.sys.domain.Service.DiaChiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DiaChiServiceImpl implements DiaChiService {
    DiaChiRepository diaChiRepository;

    DiaChiMapper diaChiMapper;

    @Override
    public Boolean createDiaChi(DiaChiReq diaChiReq) {
        diaChiRepository.save(diaChiMapper.toEntity(diaChiReq));
        return true;
    }
}
