package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.dto.HoaDonDto;
import datn.com.example.datn.sys.domain.dto.response.ApiResponse;
import datn.com.example.datn.sys.domain.service.HoaDonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don")
@RequiredArgsConstructor
public class HoaDonController {

    private final HoaDonService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HoaDonDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(), "Lấy danh sách hóa đơn"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HoaDonDto>> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id), "Lấy chi tiết hóa đơn"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HoaDonDto>> create(@RequestBody HoaDonDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.create(dto), "Tạo mới hóa đơn"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<HoaDonDto>> update(@PathVariable Integer id, @RequestBody HoaDonDto dto) {
        return ResponseEntity.ok(ApiResponse.success(service.update(id, dto), "Cập nhật hóa đơn"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa hóa đơn thành công"));
    }
}