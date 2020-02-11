package sunghs.rest.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="REQUEST_INFO")
public @Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity class RequestInfo {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	private @Column(name="REQUEST_IP") String requestIp;
	
	private @Column(name="REQUEST_METHOD") String requestMethod;
	
	private @Column(name="REQUEST_PARAMS") String requestParams;
	
	private @Column(name="REQUEST_DATE") LocalDateTime requestDate;
}
