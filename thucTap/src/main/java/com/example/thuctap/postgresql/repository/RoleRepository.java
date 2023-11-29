package com.example.thuctap.postgresql.repository;

import com.example.thuctap.postgresql.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select roles.* from accounts join roles on roles.id = accounts.id_role where accounts.code = :username and accounts.password= :password", nativeQuery = true)
    Role getAllRoleByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
