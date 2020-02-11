package sunghs.rest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import sunghs.rest.entity.RequestInfo;
import sunghs.rest.entity.ResponseInfo;
import sunghs.rest.entity.UserInfo;
import sunghs.rest.service.UserService;

public @RestController @RequestMapping("/user") class UserController extends AbstractController<RequestInfo, ResponseInfo, UserInfo> {

	private @Autowired UserService userService;
	
	/**
	 * SELECT USER BY ID
	 */
	@Override
	protected ResponseEntity<ResponseInfo> get(ServletWebRequest webRequest, RequestInfo requestInfo, @PathVariable(value="param", required=true) String param) {
		ResponseInfo responseInfo = this.preProcess(webRequest, RequestInfo.class, ResponseInfo.class);
		responseInfo.setResponseData(userService.findById(Long.valueOf(param)).toString());
		responseInfo.setResponseConstant(HttpStatus.OK.value());
		return this.postProcess(responseInfo);
	}
	
	/**
	 * INSERT USER
	 */
	@Override
	protected ResponseEntity<ResponseInfo> post(ServletWebRequest webRequest, RequestInfo requestInfo, UserInfo info) {
		ResponseInfo responseInfo = this.preProcess(webRequest, RequestInfo.class, ResponseInfo.class);
		responseInfo.setResponseData(userService.save(info).toString());
		responseInfo.setResponseConstant(HttpStatus.CREATED.value());
		return this.postProcess(responseInfo);
	}

	/**
	 * UPDATE WHOLE USER
	 */
	@Override
	protected ResponseEntity<ResponseInfo> put(ServletWebRequest webRequest, RequestInfo requestInfo, UserInfo info, @PathVariable(value="param", required=true) String param) {
		ResponseInfo responseInfo = this.preProcess(webRequest, RequestInfo.class, ResponseInfo.class);
		Optional<UserInfo> updatedUser = Optional.ofNullable(userService.update(Long.valueOf(param), info));
		responseInfo.setResponseData(updatedUser.get().toString());
		if(updatedUser.isPresent()) {
			responseInfo.setResponseConstant(HttpStatus.OK.value());
		}
		else {
			responseInfo.setResponseConstant(HttpStatus.NOT_FOUND.value());
		}
		return this.postProcess(responseInfo);
	}

	/**
	 * DELETE USER BY ID
	 */
	@Override
	protected ResponseEntity<ResponseInfo> delete(ServletWebRequest webRequest, RequestInfo requestInfo, @PathVariable(value="param", required=true) String param) {
		ResponseInfo responseInfo = this.preProcess(webRequest, RequestInfo.class, ResponseInfo.class);
		if(userService.delete(Long.valueOf(param))) {
			responseInfo.setResponseData("DELETE SUCCESS");
			responseInfo.setResponseConstant(HttpStatus.OK.value());
		}
		else {
			responseInfo.setResponseData("DELETE FAIL");
			responseInfo.setResponseConstant(HttpStatus.NOT_FOUND.value());
		}
		return this.postProcess(responseInfo);
	}
	
	
}
