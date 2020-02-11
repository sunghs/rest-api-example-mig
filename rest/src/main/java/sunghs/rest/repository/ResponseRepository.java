package sunghs.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sunghs.rest.entity.ResponseInfo;

public @Repository interface ResponseRepository extends JpaRepository<ResponseInfo, Long> {

}
