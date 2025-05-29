package datn.com.example.datn.sys.domain.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authentication {
    String token;
    String refreshToken;
    Boolean authenticated;
}
