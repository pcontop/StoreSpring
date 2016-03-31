package com.dev.backend.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dev.backend.BackendApplication;
import com.dev.backend.IntegrationUtil;
import com.dev.backend.bean.TestUtil;
import com.dev.beans.CustomerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {BackendApplication.class, PersistCustomerServiceStub.class})
@WebAppConfiguration
public class CustomerControllerUnitTest {
	private static final String BASE_URI="/customer";

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).
				build();
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void ping() throws Exception {
 		this.mockMvc.perform(get(BASE_URI + "/ping"))
 		.andDo(print())
 		.andExpect(status().isOk())
 	    .andExpect(content().string("ok"));
 	}

	@Test
	public void existingCustomerGet() throws Exception {
 		this.mockMvc.perform(get(BASE_URI + "/1"))
 		.andDo(print())
 		.andExpect(status().isOk())
 	    .andExpect(jsonPath("$.code", is("1")));
 	}

	@Test
	public void basicCustomerSave() throws Exception {
		CustomerDto customer = TestUtil.createCustomerDto1();
		
 		this.mockMvc.perform(
 				post(BASE_URI + "/")
 				.contentType(IntegrationUtil.APPLICATION_JSON_UTF8)
                 .content(IntegrationUtil.convertObjectToJsonBytes(customer))
                 )
 		.andDo(print())
 		.andExpect(status().isOk())
 	    .andExpect(jsonPath("$.code", is(customer.getCode())));
 	}

	@Test
	public void existingCustomerDelete() throws Exception {
 		this.mockMvc.perform(delete(BASE_URI + "/1"))
 		.andDo(print())
 		.andExpect(status().isOk())
 		.andExpect(content().string("true"));
 	}

	@Test
	public void getAllCustomers() throws Exception {
 		this.mockMvc.perform(get(BASE_URI + "/"))
 		.andDo(print())
 		.andExpect(status().isOk())
 		.andExpect(jsonPath("$", hasSize(2)))
 		.andExpect(jsonPath("$[0].code", is("10")))
 		.andExpect(jsonPath("$[1].code", is("20")));
 	}

}