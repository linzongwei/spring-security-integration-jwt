/**
 * wesoft.com Inc. Copyright (c) 2005-2016 All Rights Reserved.
 */
package com.linzongwei.authentication.security.domain;

import java.util.List;
import java.util.Objects;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Description: 认证对象用户信息存储
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@ToString
public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = -6836637312362049568L;
    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    private List<GrantedAuthority> authorities;

    public SecurityUser() {
        
    }

    public SecurityUser(User user) {
        super(user);
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        SecurityUser that = (SecurityUser) o;
        return accountNonExpired == that.accountNonExpired
                && accountNonLocked == that.accountNonLocked
                && credentialsNonExpired == that.credentialsNonExpired
                && enabled == that.enabled;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(), accountNonExpired, accountNonLocked, credentialsNonExpired,
                enabled);
    }

}
