package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
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
