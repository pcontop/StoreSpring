package com.dev.frontend.services;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Abstract Template for all RestServices, that should extend this class. 
 * 
 * Implements the call for all the necessary CRUD operations on the baseURI, as defined by the Utils class.
 * 
 * Each implementation should define the services directory, as based on the URI, and the return method 
 * for the getAll() function, and any other access methods other than these exposed by this class.  
 *  
 * @see Utils
 * @author pcont_000
 *
 * @param <E>
 */
public abstract class RestServices<E> {

	private String baseUri = Utils.getBaseUriServices();
	protected RestTemplate restTemplate = new RestTemplate();

	private Class<E> classType;

	@SuppressWarnings("unchecked")
	public RestServices(){
		this.classType = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Builds the access url based on the base uri, on the service group directory and the service uri. 
	 * @param uri
	 * @return
	 */
	public String buildUrl(String uri){
		return baseUri + getServiceDirectory() + uri;
	}

	/**
	 * Performs a get on the rest server based on the passed key. Returns the implementations
	 * object, downcast to Object, as per the project's necessities. 
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key){
		String url = buildUrl("/" + encode(key)); 
		ResponseEntity<E> responseEntity = restTemplate.getForEntity(url, classType);
		return treatResponse(url, responseEntity);		
	}

	/**
	 * Treats the response entity object. Returns to standard output an error if the status code is any
	 * other than 200. 
	 * @param url - The full url used. 
	 * @param responseEntity
	 * @return The returned object, downcast to Object. 
	 */
	private Object treatResponse(String url, ResponseEntity<?> responseEntity) {
		HttpStatus statusCode = responseEntity.getStatusCode();
		if (statusCode.value()!=(HttpStatus.OK.value())){
			System.out.println("Error in accessing Service - " + url + ":" 
		+ statusCode + " - " + responseEntity.getStatusCode().getReasonPhrase());
			return null;
		}
		return responseEntity.getBody();
	}

	/**
	 * Downcasts the result from getAllofType from a List<> of Dto object to a List<Object>.
	 * @return
	 */
	public List<Object> getAll(){
		return getAllOfType().stream().map(c-> (Object) c).collect(Collectors.toList());
	}

	/**
	 * Calls the save rest operation. 
	 * @param o The Dto object to be removed. 
	 * @return The returned object, downcast to Object.
	 */
	public Object save(Object o) {
		String url = buildUrl(""); 
		ResponseEntity<E> responseEntity = restTemplate.postForEntity(buildUrl(""), o, classType);
		return treatResponse(url, responseEntity);		
	}
	
	/**
	 * Calls the delete rest operation.
	 * @param key The key to the object to be deleted.
	 * @return True, if successful, or false. 
	 */
	public Boolean delete(String key){
		String url = buildUrl("/" + encode(key)); 
		ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
				url
				, HttpMethod.DELETE
				, null
				, Boolean.class);
		return (Boolean) treatResponse(url, responseEntity);				
	}

	private String encode(String key) {
		try {
			return URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Must be implemented on the extending class, defines the rest object operations directory, as 
	 * implemented on the backend.
	 * for instance, for the SalesOrders, would be salesOrder/
	 * @return The directory.
	 */
	protected abstract String getServiceDirectory();

	/**
	 * Returns an implementation of the allOfType class. This was made to bypass some of Spring's runtime 
	 * limitations with lists and generics. 
	 * @return A List of Dto objects. 
	 */
	public abstract List<E> getAllOfType();


	
}
