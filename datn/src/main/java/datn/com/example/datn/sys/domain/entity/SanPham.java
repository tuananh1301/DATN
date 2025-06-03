package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "san_pham")
@JsonIgnoreProperties({"haibernateLazyInitializer", "handler"})
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ma_san_pham", length = 100)
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Lob
    @Column(name = "mo_ta")
    private String moTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc idDanhMuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chat_lieu")
    private ChatLieu idChatLieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_xuat_xu")
    private XuatXu idXuatXu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}