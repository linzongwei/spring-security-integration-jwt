package com.linzongwei.authentication.security.filter;

import com.linzongwei.authentication.security.config.JwtAuthenticationConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Description: 过滤器，根据头部token进行授权操作
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@Slf4j
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationConfig config;

    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config) {
        this.config = config;
    }

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = request.getHeader(config.getHeader());
        JwtTokenAuthenticationFilter.log.info("token:{}", token);
        if (token != null && token.startsWith(config.getPrefix())) {
            token = token.substring(config.getPrefix().length());
            try {
                Claims claims =
                        Jwts.parser()
                                .setSigningKey(config.getSecret().getBytes())
                                .parseClaimsJws(token)
                                .getBody();
                String user = claims.getSubject();
                JwtTokenAuthenticationFilter.log.info("user:{}", user);
                @SuppressWarnings("unchecked")
                List<String> authorities = claims.get("authorities", List.class);
                if (!StringUtils.isBlank(user)) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    authorities.stream()
                                            .map(SimpleGrantedAuthority::new)
                                            .collect(Collectors.toList()));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception ignore) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

}
