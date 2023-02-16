package com.data.upload.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.data.upload.model.StockDetails;
import com.data.upload.repository.StockDetailsRepository;
import com.data.upload.service.StockDetailsService;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class StockDetailsControllerTest {
	private InputStream is;
	@Autowired
	private MockMvc mockMvc;
	@Mock
	private StockDetailsService service;

	@Mock
	StockDetailsRepository repo;

	@Spy
	@InjectMocks
	private StockDetailsController controller = new StockDetailsController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		is = controller.getClass().getClassLoader().getResourceAsStream("dow_jones.csv");
	}

	@Test
	public void test_upload() throws Exception {
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "dow_jones.csv", "multipart/form-data", is);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.multipart("/v1/upload").file(mockMultipartFile)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
		Assert.assertEquals(201, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString());
		Assert.assertEquals("Data upload successful!", result.getResponse().getContentAsString());
	}

	@Test
	public void test_quaterlyStock() throws Exception {

		StockDetails mockStock = new StockDetails();
		mockStock.setQuarter(1);
		mockStock.setStock("AA");
		mockStock.setDate("2011-01-07");
		mockStock.setOpen("15.82");
		mockStock.setHigh("16.72");
		mockStock.setLow("15.78");
		mockStock.setClose("16.42");
		mockStock.setVolume(239655616);
		mockStock.setPercent_change_price(3.79267);
		mockStock.setPercent_change_volume_over_last_wk(0.0);
		mockStock.setPrevious_weeks_volume(0);
		mockStock.setNext_weeks_open("16.71");
		mockStock.setNext_weeks_close("15.97");
		mockStock.setPercent_change_next_weeks_price(-4.42849);
		mockStock.setDays_to_next_dividend(26);
		mockStock.setPercent_return_next_dividend(0.182704);

		List<StockDetails> list = new ArrayList<StockDetails>();
		list.add(mockStock);

		Mockito.when(service.getQuaterlyStock(Mockito.anyInt(), Mockito.anyString())).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/quaterlyStock?quarter=1&stock=AA")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	public void test_addRecordk() throws Exception {

		String json = " {\r\n" + "        \"quarter\": 2,\r\n" + "        \"stock\": \"AA\",\r\n"
				+ "        \"date\": \"2011-07-01\",\r\n" + "        \"open\": \"17.13\",\r\n"
				+ "        \"high\": \"17.8\",\r\n" + "        \"low\": \"17.02\",\r\n"
				+ "        \"close\": \"17.47\",\r\n" + "        \"volume\": 103320396,\r\n"
				+ "        \"percent_change_price\": 1.98482,\r\n"
				+ "        \"percent_change_volume_over_last_wk\": 8.131838957,\r\n"
				+ "        \"previous_weeks_volume\": 92550392,\r\n" + "        \"next_weeks_open\": \"17.42\",\r\n"
				+ "        \"next_weeks_close\": \"17.92\",\r\n"
				+ "        \"percent_change_next_weeks_price\": 2.87026,\r\n"
				+ "        \"days_to_next_dividend\": 41,\r\n" + "        \"percent_return_next_dividend\": 0.0\r\n"
				+ "    }";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/addRecord").accept(MediaType.APPLICATION_JSON)
				.content(json).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		Assert.assertEquals(201, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString());
		Assert.assertEquals("Record added successfully!", result.getResponse().getContentAsString());
	}
}