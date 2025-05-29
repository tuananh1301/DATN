package datn.com.example.datn.sys.domain.Service.ServiceImpl;

import datn.com.example.datn.common.enums.Role;
import datn.com.example.datn.sys.domain.Dto.NguoiDungDto;
import datn.com.example.datn.sys.domain.Entity.NguoiDung;
import datn.com.example.datn.sys.domain.Mapper.NguoiDungMapper;
import datn.com.example.datn.sys.domain.Repository.NguoiDungRepository;
import datn.com.example.datn.sys.domain.Service.NguoiDungService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NguoiDungServiceImpl implements NguoiDungService {
    NguoiDungRepository nguoiDungRepository;
    NguoiDungMapper nguoiDungMapper;
    @Override
    public NguoiDungDto create(NguoiDungDto nguoiDungDto) {
        NguoiDung nguoiDung = nguoiDungMapper.toNguoiDung(nguoiDungDto);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.CUSTOMER.toString());
        nguoiDung.setVaiTro(roles);
        try {
            nguoiDungRepository.save(nguoiDung);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nguoiDungMapper.toNguoiDungDto(nguoiDung);
    }
}
