package com.linzongwei.authentication.security.service;

import com.linzongwei.authentication.security.domain.SecurityUser;
import com.linzongwei.authentication.security.domain.User;
import com.linzongwei.authentication.security.repository.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Description: 加载用户信息
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@Service(value = "userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<User> userOptional = userJpaRepository.findByUsername(s);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        User user = userOptional.get();

        SecurityUser securityUser =
                new SecurityUser(user);

        return securityUser;
    }

    @Autowired
    public void setUserRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

}
