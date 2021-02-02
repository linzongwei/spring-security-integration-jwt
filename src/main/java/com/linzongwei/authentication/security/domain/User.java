package com.linzongwei.authentication.security.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Data;

/**
 * Description: user数据库表实体类
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@Data
@Table
@Entity(name = "user")
public class
User {

    @Id
    private Integer id;

    private String username;

    private String password;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

}
