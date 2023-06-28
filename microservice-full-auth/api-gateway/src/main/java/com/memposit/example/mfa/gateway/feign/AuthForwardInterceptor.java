package com.memposit.example.mfa.gateway.feign;

import com.memposit.example.mfa.auth.TokenProvider;
import com.memposit.example.mfa.auth.configuration.AuthProperties;
import com.memposit.example.mfa.auth.model.UserInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthForwardInterceptor implements RequestInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public void apply(RequestTemplate template) {
        // hystrix.shareSecurityContext = true
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            Object principal = authentication.getPrincipal();
            String token;
            if (principal instanceof UserInfo) {
                token = ((UserInfo) principal).getToken();
            } else {
                // convert basic authentication to jwt token
                // TODO generated to much tokens, some of them can be compromised
                token = tokenProvider.createToken(authentication);
            }
            template.header(HttpHeaders.AUTHORIZATION, String.format("%s %s", AuthProperties.TOKEN_PREFIX, token));
        }
    }

    // redirect headers only
    /*@Override
    public void apply(RequestTemplate template) {
        // hystrix isolation must be set to SEMAPHORE
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
    }*/
}
