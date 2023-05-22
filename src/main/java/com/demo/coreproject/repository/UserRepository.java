package com.demo.coreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.coreproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
