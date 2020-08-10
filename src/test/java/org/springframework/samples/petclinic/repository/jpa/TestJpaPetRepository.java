package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaPetRepository {
	
	@Autowired
	PetRepository petRepo;
	
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

	@Transactional
	@Rollback(true)
	@Test
	public void TestSave()
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


	@Test
	@Transactional
	@Rollback(true)
	public void TestPetType()
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
	
		List<PetType> expectedPet = petRepo.findPetTypes();
		assertNotNull(expectedPet);
		assertEquals("bird", petRepo.findPetTypes().get(0).getName());
		assertEquals(new Integer(5), petRepo.findPetTypes().get(0).getId());
	}
	

}
