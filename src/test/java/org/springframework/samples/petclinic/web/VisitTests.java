package org.springframework.samples.petclinic.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/mvc-core-config.xml","classpath:mvc-test-config.xml"})
public class VisitTests {

    private static final int PET_ID = 1;

    @Autowired
    private VisitController visitController;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        given(this.clinicService.findPetById(PET_ID)).willReturn(new Pet());
    }

    @Test
    public void testInitNewVisitForm() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/visits/new", PET_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    public  void processNewVisitFormSucessTest() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", PET_ID)
            .param("name", "rajesh")
            .param("description", "general checkup")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    public void testProcessNewVisitFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", PET_ID)
            .param("name", "rajesh")
        )
            .andExpect(model().attributeHasErrors("visit"))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    public void testShowVisits() throws Exception {
        mockMvc.perform(get("/owners/*/pets/{petId}/visits", PET_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visits"))
            .andExpect(view().name("visitList"));
    }


}