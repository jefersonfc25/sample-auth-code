package dev.jefersoncardoso.sampleoauthcode.infra.web;

import dev.jefersoncardoso.sampleoauthcode.infra.http.feign.AuthClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private static final Log logger = LogFactory.getLog(AuthController.class);

    private final AuthClient authClient;
    private final String clientCredential;
    private final String clientSecret;

    public AuthController(
            AuthClient authClient,
            @Value("${app.oauth.client-credential}")
            String clientCredential,
            @Value("${app.oauth.client-secret}")
            String clientSecret
    ) {
        this.authClient = authClient;
        this.clientCredential = clientCredential;
        this.clientSecret = clientSecret;
    }

    @GetMapping
    public ResponseEntity<Void> getToken(
            @RequestParam String code
    ) {
        logger.debug("=====>>> Auth code: " + code);
        String encodedString = Base64.getEncoder().encodeToString(String.format("%s:%s", clientCredential, clientSecret).getBytes());
        logger.debug(authClient.getToken("Basic "+encodedString, "application/x-www-form-urlencoded"));
        return ResponseEntity.noContent().build();
    }
}
