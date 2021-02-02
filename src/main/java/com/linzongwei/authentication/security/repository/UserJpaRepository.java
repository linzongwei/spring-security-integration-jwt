package com.linzongwei.authentication.security.repository;

import com.linzongwei.authentication.security.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author linli
 * @since 2020/11/27 14:17
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String loginName);

}
