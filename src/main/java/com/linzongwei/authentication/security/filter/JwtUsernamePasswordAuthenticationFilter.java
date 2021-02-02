package com.linzongwei.authentication.security.filter;

import com.linzongwei.authentication.security.config.JwtAuthenticationConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Description: 拦截请求执行认证
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
public class JwtUsernamePasswordAuthenticationFilter
        extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationConfig config;

    public JwtUsernamePasswordAuthenticationFilter(
            JwtAuthenticationConfig config, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(config.getUrl(), "POST"));
        setAuthenticationManager(authManager);
        this.config = config;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException {
        Instant now = Instant.now();

        String token =
                Jwts.builder()
                        .setSubject(auth.getName())
                        .claim(
                                "authorities",
                                auth.getAuthorities().stream()
                                        .map(GrantedAuthority::getAuthority)
                                        .collect(Collectors.toList()))
                        .setIssuedAt(Date.from(now))
                        .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
                        .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
                        .compact();
        res.addHeader(config.getHeader(), config.getPrefix() + token);
    }


}
