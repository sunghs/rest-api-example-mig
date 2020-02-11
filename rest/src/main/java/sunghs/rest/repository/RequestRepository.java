package sunghs.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sunghs.rest.entity.RequestInfo;

public @Repository interface RequestRepository extends JpaRepository<RequestInfo, Long> {

}
