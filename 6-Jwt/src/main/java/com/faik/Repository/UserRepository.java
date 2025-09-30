package com.faik.Repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faik.Model.User;
import java.util.List;


@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

	Optional<User> findByUsername(String username);
}
