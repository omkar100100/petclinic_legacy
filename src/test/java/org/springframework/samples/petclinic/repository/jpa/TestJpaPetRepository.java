package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.List;
import org.springframework.samples.petclinic.model.PetType;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaPetRepository {
	
	@Autowired
	PetRepository petRepo;
	


	@Autowired
	PetRepository petRepository;
	
	@Autowired
	OwnerRepository jpaOwnerRepo;
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void TestfindById()
	{
		final String PET_NAME="PINKY";
		
		
		List<PetType> petTypes = petRepo.findPetTypes();
		
		
		Pet pet=new Pet();
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jpaOwnerRepo.save(owner);
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
	
		Pet expectedPet = petRepo.findById(pet.getId());
		assertNotNull(expectedPet);
		assertEquals(pet.getName(), expectedPet.getName());
	
	}
	
	private Owner getOwnerObject() {
		Owner owner=new Owner();
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");
		
		return owner;
	}
	
//	@Test
//	public void TestPetType()
//	{
//		Pet p=new Pet();
//		p.setType(new PetType());
//
//		Pet p1=new Pet();
//		p1.setType(new PetType());
//
//		petRepository.save(p);
//		petRepository.save(p1);
//		
//		assertEquals(2, petRepository.findPetTypes().size());
//	}

}
