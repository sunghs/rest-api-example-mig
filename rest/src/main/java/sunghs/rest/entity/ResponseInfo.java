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

@Table(name="RESPONSE_INFO")
public @Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity class ResponseInfo {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	
	private @Column(name="REQUEST_ID") long requestId;
	
	private @Column(name="RESPONSE_CONSTANT") int responseConstant;
	
	private @Column(name="ERROR_NAME") String errorName;
	
	private @Column(name="ERROR_STACK_TRACE") String errorStackTrace;
	
	private @Column(name="RESPONSE_DATA") String responseData;
	
	private @Column(name="RESPONSE_DATE") LocalDateTime responseDate;
}
