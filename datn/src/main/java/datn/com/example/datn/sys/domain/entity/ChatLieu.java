package datn.com.example.datn.sys.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "chat_lieu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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