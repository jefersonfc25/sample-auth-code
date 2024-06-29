package dev.jefersoncardoso.sampleoauthcode.infra.http.feign;

import dev.jefersoncardoso.sampleoauthcode.infra.dto.AuthResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        value = "LoginCorporativoClient",
        url = "${app.auth-server.url}"
)
public interface AuthClient {

    @PostMapping(value = "/authorize/token")
    List<AuthResponseDTO> getToken(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("Content-Type") String contentType
    );
}
