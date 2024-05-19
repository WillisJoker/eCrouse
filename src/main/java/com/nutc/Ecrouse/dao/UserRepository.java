package com.nutc.Ecrouse.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutc.Ecrouse.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByname(String name);
}
