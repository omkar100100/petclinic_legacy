package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(locations = {"classpath:spring/mvc-core-config.xml", 
		"classpath:spring/mvc-test-config.xml",
		"classpath:spring/business-config.xml",
		"classpath:spring/datasource-config.xml", 
		"classpath:spring/mvc-view-config.xml",
		"classpath:spring/tools-config.xml"})
public class TestJpaPetRepository {
	
	@Autowired
	PetRepository petRepo;
	
//    @Override
//    public Pet findById(int id) {
//        return this.em.find(Pet.class, id);
//    }

	@Autowired
	PetRepository petRepository;
	
	
	@Test
	public void TestfindById()
	{
		Pet p=new Pet();
		p.setId(1);
		petRepository.save(p);
		
		assertEquals(p, petRepository.findById(1));
	}
	
	@Test
	public void TestPetType()
	{
		Pet p=new Pet();
		p.setType(new PetType());

		Pet p1=new Pet();
		p1.setType(new PetType());

		petRepository.save(p);
		petRepository.save(p1);
		
		assertEquals(2, petRepository.findPetTypes().size());
	}

}
