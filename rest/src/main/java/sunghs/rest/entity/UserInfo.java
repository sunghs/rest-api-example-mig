package sunghs.rest.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="USER_INFO")
public @Data @Builder @NoArgsConstructor @AllArgsConstructor @Entity class UserInfo {

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;
	
	private @Column(name="USER_ID") String userId;
	
	private @Column(name="USER_PASSWORD")String userPassword;
	
	private @Column(name="USER_NAME") String userName;
	
	private @Column(name="USER_PHONE") String userPhone;
	
	private @Column(name="USER_EXT") String userExt;
	
	private @Column(name="REG_DATE") @CreationTimestamp LocalDateTime regDate;
	
	private @Column(name="UPT_DATE") @UpdateTimestamp LocalDateTime uptDate;
}
