package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.MauSac;
import datn.com.example.datn.sys.domain.service.MauSacService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mau-sac")
public class MauSacController {
    private final MauSacService service;

    public MauSacController(MauSacService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MauSac>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MauSac> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MauSac> create(@RequestBody MauSac mauSac) {
        return ResponseEntity.ok(service.create(mauSac));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MauSac> update(@PathVariable Integer id, @RequestBody MauSac mauSac) {
        return ResponseEntity.ok(service.update(id, mauSac));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (deleted) return ResponseEntity.ok("Đã xoá màu sắc");
        return ResponseEntity.badRequest().body("Không tìm thấy ID cần xoá");
    }
}
