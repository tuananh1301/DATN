package datn.com.example.datn.sys.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLieuDto {
    private Integer id;
    private String tenChatLieu;
    private Boolean trangThai;
}