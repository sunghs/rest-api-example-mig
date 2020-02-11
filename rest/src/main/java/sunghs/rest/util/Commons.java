package sunghs.rest.util;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

public @Slf4j final class Commons {

	public static LocalDateTime getNowTime() {
		return LocalDateTime.now();
	}
	
	public static Optional<String> mapToString(Map<?, ?> map) {
		try {
			return Optional.of(new ObjectMapper().writeValueAsString(map));
		}
		catch(Exception e) {
			log.error("convert map to json error", e);
			e.printStackTrace();
			return Optional.ofNullable(null);
		}
	}

	public static void main(String[] args) {
		System.out.println(LocalDateTime.now());
	}
}
