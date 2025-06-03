package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.KichThuoc;
import datn.com.example.datn.sys.domain.service.KichThuocService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kich-thuoc")
public class KichThuocController {
    private final KichThuocService service;

    public KichThuocController(KichThuocService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<KichThuoc>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KichThuoc> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<KichThuoc> create(@RequestBody KichThuoc kichThuoc) {
        return ResponseEntity.ok(service.create(kichThuoc));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KichThuoc> update(@PathVariable Integer id, @RequestBody KichThuoc kichThuoc) {
        return ResponseEntity.ok(service.update(id, kichThuoc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (deleted) return ResponseEntity.ok("Đã xoá kích thước");
        return ResponseEntity.badRequest().body("Không tìm thấy ID cần xoá");
    }
}
