package datn.com.example.datn.sys.domain.controller;

import datn.com.example.datn.sys.domain.entity.HoaDon;
import datn.com.example.datn.sys.domain.service.HoaDonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonController {
    private final HoaDonService service;

    public HoaDonController(HoaDonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<HoaDon>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HoaDon> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<HoaDon> create(@RequestBody HoaDon hoaDon) {
        return ResponseEntity.ok(service.create(hoaDon));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HoaDon> update(@PathVariable Integer id, @RequestBody HoaDon hoaDon) {
        return ResponseEntity.ok(service.update(id, hoaDon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Đã xoá hoá đơn (pending)");
        }
        return ResponseEntity.badRequest().body("Không thể xoá hoá đơn đã completed");
    }
}
