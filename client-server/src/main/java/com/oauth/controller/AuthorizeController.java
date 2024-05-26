package com.oauth.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 伍六七
 * @date 2023/6/4 17:21
 */
@Controller
public class AuthorizeController {

    @GetMapping("/authorize")
    public String authorize(Model model){
        String clientId = "test_client_id";
        String clientSecret = "test_client_secret";
        String scope = "";
        String accessTokenUrl = "http://127.0.0.1:8081/oauth2/device_authorization?client_id={client_id}&scope={scope}";


        Map<String, String> params = new HashMap<>(4);
        params.put("client_id", clientId);
        params.put("scope", scope);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBasicAuth(clientId,clientSecret);

        Objects.requireNonNull(params, "Device Authorization Response cannot be null");

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);

        Map<String,Object> responseParameters = restTemplate.postForObject(accessTokenUrl, httpEntity, Map.class, params);

        Instant issuedAt = Instant.now();
        Integer expiresIn = (Integer) responseParameters.get("expires_in");//获取过期时间
        Instant expiresAt = issuedAt.plusSeconds(expiresIn);
        String deviceCode = (String) responseParameters.get("device_code");//获取授权码
        String userCode = (String) responseParameters.get("user_code");//获取授权码
        String verificationUri = (String) responseParameters.get("verification_uri");//获取授权码
        String verificationUriComplete = (String) responseParameters.get("verification_uri_complete");//获取授权码


        model.addAttribute("deviceCode", deviceCode);
        model.addAttribute("expiresAt", expiresAt);
        model.addAttribute("userCode",userCode);
        model.addAttribute("verificationUri", verificationUri);
        // Note: You could use a QR-code to display this URL
        model.addAttribute("verificationUriComplete", verificationUriComplete);


        return "index";
    }

}
