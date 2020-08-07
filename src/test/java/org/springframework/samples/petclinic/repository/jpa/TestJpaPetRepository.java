package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.context.ContextConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = 
{"classpath:spring/business-config.xml",
		"classpath:spring/mvc-test-config.xml"})
@ActiveProfiles("jpa")
public class TestJpaPetRepository {
	
	@Autowired
	PetRepository petRepo;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindpetById() {
		Pet pet = petRepo.findById(1);
		
		assertTrue(pet!=null);
		assertTrue(pet.getName().equals("Leo"));
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void TestPetType()
	{
		assertEquals(6, petRepo.findPetTypes().size());
		assertEquals("bird", petRepo.findPetTypes().get(0).getName());
		assertEquals(new Integer(5), petRepo.findPetTypes().get(0).getId());
		assertEquals("cat", petRepo.findPetTypes().get(1).getName());
		assertEquals(new Integer(1), petRepo.findPetTypes().get(1).getId());
	}

}
