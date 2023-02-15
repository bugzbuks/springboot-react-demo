package com.example.demo;

import com.example.demo.controllers.GradeController;
import com.example.demo.repo.GradesRepo;
import com.example.demo.services.GradesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringByMavenDemoApplicationTests {


	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name("form"))
				.andExpect(model().attributeExists("grades"));
	}


	@Test
	public void testSubmitGradesForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/submitGrades")
				.param("name","Harry")
				.param("subject","Potions")
				.param("score","100");
		mockMvc.perform(request)
				.andExpect(status().is3xxRedirection());
	}

}
