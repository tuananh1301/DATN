package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.SanPham;
import datn.com.example.datn.sys.domain.service.SanPhamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {

    private final SanPhamService service;

    public SanPhamController(SanPhamService service) {
        this.service = service;
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<SanPham>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        SanPham sp = service.getById(id);
        if (sp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sản phẩm với ID: " + id);
        }
        return ResponseEntity.ok(sp);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> create(@RequestBody SanPham sanPham) {
        try {
            SanPham created = service.create(sanPham);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tạo sản phẩm thất bại: " + e.getMessage());
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SanPham sanPham) {
        SanPham updated = service.update(id, sanPham);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sản phẩm để cập nhật với ID: " + id);
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Không tìm thấy sản phẩm để xoá với ID: " + id);
        }
        return ResponseEntity.ok("Đã xoá sản phẩm với ID: " + id);
    }
}
