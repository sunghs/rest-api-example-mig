package sunghs.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sunghs.rest.entity.UserInfo;

public @Repository interface UserRepository extends JpaRepository<UserInfo, Long> {

}
