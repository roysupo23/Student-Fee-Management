package com.java.student.fee.management.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.java.student.fee.management.service.StudentDetailsService;


@ExtendWith(MockitoExtension.class)
class StudentDetailsControllerTest {
	
	@InjectMocks
	private StudentDetailsController studentDetailsController;
	
	@Mock
    private StudentDetailsService mockStudentService;
	
	private MockMvc mockMvc;
	
	private static final String REQUEST_PAYLOAD_DIRECTORY = "src/test/resources/student/student-add-request.json";
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentDetailsController).build();
	}
	
	@Test
	void testAddStudentDetails() throws Exception  {
		
		String requestPayload = new String(Files.readAllBytes(Paths.get(REQUEST_PAYLOAD_DIRECTORY)));
		final MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/student/add")
				.characterEncoding("utf-8").contentType(MediaType.APPLICATION_JSON).content(requestPayload)).andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}
}
