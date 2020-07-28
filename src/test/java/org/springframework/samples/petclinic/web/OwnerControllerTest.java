package org.springframework.samples.petclinic.web;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;


//import static org.hamcrest.Matchers.hasProperty;
//import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.hamcrest.Matchers;


import org.junit.runner.RunWith;

//@SpringJUnitWebConfig(locations= {"classpath:spring/mvc-core-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/mvc-core-config.xml","classpath:mvc-test-config.xml"})
public class OwnerControllerTest {
	
	@Autowired
	private OwnerController ownerController;
	
	@Autowired
	private ClinicService clinicService;
	
	private MockMvc mockMvc;
	
	private Owner owner;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
		
		owner = new Owner();
		owner.setId(1);
		owner.setFirstName("Chandra Sekhar");
		owner.setLastName("A");
		owner.setCity("Hyderabad");
		owner.setAddress("Hyderabad");
		owner.setTelephone("9110731979");
		
		given(this.clinicService.findOwnerById(owner.getId())).willReturn(owner);
		
	}
	
	
	    @Test
	    public void testInitFindForm() throws Exception {
	        mockMvc.perform(get("/owners/find"))
	            .andExpect(status().isOk())
	            .andExpect(model().attributeExists("owner"))
	            .andExpect(view().name("owners/findOwners"));
	    }
	    
	    @Test
	    public void testInitUpdateOwnerForm() throws Exception{
	    	mockMvc.perform(get("/owners/1/edit"))
	    	.andExpect(status().isOk())
	    	.andExpect(model().attribute("owner", Matchers.hasProperty("address",Matchers.is("Hyderabad"))))
	        .andExpect(view().name("owners/createOrUpdateOwnerForm"));
	    	
	    	
	    }
	    
	    
	    @Test
	    public void testShowOwner() throws Exception {
	        mockMvc.perform(get("/owners/{ownerId}", 1))
	            .andExpect(status().isOk())
	            .andExpect(model().attribute("owner", Matchers.hasProperty("lastName", Matchers.is("A"))))
	            .andExpect(model().attribute("owner", Matchers.hasProperty("firstName", Matchers.is("Chandra Sekhar"))))
	            .andExpect(view().name("owners/ownerDetails"));
	    }
}
