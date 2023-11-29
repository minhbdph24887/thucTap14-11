package com.example.demo1.repository;

import com.example.demo1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from role where name= :nameRole", nativeQuery = true)
    Role findRoleByName(@Param("nameRole") String nameRole);

    @Query(value = "select r.* from accounts a join authentication b on a.id = b.id_account join role r on b.id_role = r.id where a.email= :email", nativeQuery = true)
    List<Role> getAllRoleByEmail(@Param("email") String email);
}
