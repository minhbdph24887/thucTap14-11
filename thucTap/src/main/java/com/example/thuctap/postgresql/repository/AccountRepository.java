package com.example.thuctap.postgresql.repository;

import com.example.thuctap.postgresql.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select * from accounts where code= :username", nativeQuery = true)
    Account detailAccountByUsername(@Param("username") String username);

    @Query(value = "select * from accounts where code= :username", nativeQuery = true)
    Optional<Account> findAccountByUserName(@Param("username") String username);
}
