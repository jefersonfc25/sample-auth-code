package dev.jefersoncardoso.sampleoauthcode.infra.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final String urlAuthServer;
    private final String responseType;
    private final String clientId;
    private final String scope;
    private final String redirectUri;

    public IndexController(
            @Value("${app.auth-server.url}")
            String urlAuthServer,
            @Value("${app.oauth.response-type}")
            String responseType,
            @Value("${app.oauth.client-credential}")
            String clientId,
            @Value("${app.oauth.scope}")
            String scope,
            @Value("${app.oauth.redirect-uri}")
            String redirectUri
    ) {
        this.urlAuthServer = urlAuthServer;
        this.responseType = responseType;
        this.clientId = clientId;
        this.scope = scope;
        this.redirectUri = redirectUri;
    }

    @GetMapping("/")
    public String index(Model model) {
        String urlAuthorize =
                String.format("%s/authorize?response_type=%s&client_id=%s&scope=%s&state=xyz&redirect_uri=%s",
                        urlAuthServer,
                        responseType,
                        clientId,
                        scope,
                        redirectUri
                );

        model.addAttribute("urlAuthorize", urlAuthorize);
        return "index";
    }
}
