package datn.com.example.datn.sys.domain.Dto.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRes {
    String token;
    String refreshToken;
    Boolean authenticated;
}
