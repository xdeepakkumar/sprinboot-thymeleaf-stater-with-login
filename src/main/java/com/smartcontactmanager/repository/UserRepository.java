package com.smartcontactmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcontactmanager.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
