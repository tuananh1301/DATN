package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.SanPhamChiTiet;
import datn.com.example.datn.sys.domain.service.SanPhamChiTietService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham-chi-tiet")
public class SanPhamChiTietController {

    private final SanPhamChiTietService service;

    public SanPhamChiTietController(SanPhamChiTietService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SanPhamChiTiet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SanPhamChiTiet> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<SanPhamChiTiet> create(@RequestBody SanPhamChiTiet s) {
        return ResponseEntity.ok(service.create(s));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SanPhamChiTiet> update(@PathVariable Integer id, @RequestBody SanPhamChiTiet s) {
        return ResponseEntity.ok(service.update(id, s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.ok("Đã xoá sản phẩm chi tiết");
    }
}
