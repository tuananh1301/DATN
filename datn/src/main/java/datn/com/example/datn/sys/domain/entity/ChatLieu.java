package datn.com.example.datn.sys.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat_lieu")
public class ChatLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "ten_chat_lieu", length = 100)
    private String tenChatLieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

}