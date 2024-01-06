package com.cats.mooncell.data;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

//    void updateByUsername(String username, String address, String city, String state, String postalCode, String country, String phone, String email, String name, String role, LocalDate birthday);
}
