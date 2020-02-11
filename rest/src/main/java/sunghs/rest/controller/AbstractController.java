package sunghs.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import lombok.extern.slf4j.Slf4j;
import sunghs.rest.entity.RequestInfo;
import sunghs.rest.entity.ResponseInfo;
import sunghs.rest.repository.RequestRepository;
import sunghs.rest.repository.ResponseRepository;
import sunghs.rest.util.Commons;

/**
 * 
 * @author sunghs
 *
 * @param <I> : requestInfo (INPUT)
 * @param <O> : responseInfo (OUTPUT)
 * @param <D> : POJO Data (MODEL)
 */
public @Slf4j abstract class AbstractController<I extends RequestInfo, O extends ResponseInfo, D> {
	
	private final String CORS_ACCESS_CONTROL_STRING = "Access-Control-Allow-Origin";
	
	private @Autowired ResponseRepository responseRepository;
	
	private @Autowired RequestRepository requestRepository;
	
	@GetMapping(value = "/{param}")
	protected @ResponseBody abstract ResponseEntity<O> get(ServletWebRequest webRequest, I requestInfo, String param);
	
	@PostMapping
	protected @ResponseBody abstract ResponseEntity<O> post(ServletWebRequest webRequest, I requestInfo, @RequestBody final D info);
	
	@PutMapping(value = "/{param}")
	protected @ResponseBody abstract ResponseEntity<O> put(ServletWebRequest webRequest, I requestInfo, @RequestBody final D info,  String param);
	
	@DeleteMapping(value = "/{param}")
	protected @ResponseBody abstract ResponseEntity<O> delete(ServletWebRequest webRequest, I requestInfo, String param);
	
	protected final O preProcess(ServletWebRequest webRequest, Class<I> iClass, Class<O> oClass) {
		I requestInfo = null;
		O responseInfo = null;
		
		try {
			requestInfo = iClass.getDeclaredConstructor().newInstance();
			requestInfo.setRequestIp(webRequest.getRequest().getRemoteAddr());
			requestInfo.setRequestParams(Commons.mapToString(webRequest.getRequest().getParameterMap()).get());
			requestInfo.setRequestDate(Commons.getNowTime());
			requestInfo.setRequestMethod(webRequest.getHttpMethod().toString());
			requestInfo = requestRepository.save(requestInfo);
			log.info(requestInfo.toString());
			responseInfo = oClass.getDeclaredConstructor().newInstance();
			responseInfo.setRequestId(requestInfo.getId());
			return responseInfo;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("preProcess Error", e);
			return null;
		}
	}
	
	protected final ResponseEntity<O> postProcess(O responseInfo) {
		responseInfo.setResponseDate(Commons.getNowTime());
		responseInfo = responseRepository.save(responseInfo);
		log.info(responseInfo.toString());
		return new ResponseEntity<O>(responseInfo, this.makeResponseHeader(), HttpStatus.valueOf(responseInfo.getResponseConstant()));
	}
	
	private HttpHeaders makeResponseHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set(CORS_ACCESS_CONTROL_STRING, "*");
		return httpHeaders;
	}
}
