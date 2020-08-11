package org.springframework.samples.petclinic.repository.jpa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

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
		
	final String PET_NAME="PINKY";
	
	@Transactional
	@Rollback(true)
	@Test
	public void TestfindById()
	{
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
		assertEquals(pet.getId(), expectedPet.getId());
	
	}

	@Transactional
	@Rollback(true)
	@Test
	public void TestSave()
	{
		List<PetType> petTypes = getPetTypes();
		
		
		Pet pet=new Pet();
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jpaOwnerRepo.save(owner);
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
	
		Pet dbPet = petRepo.findById(pet.getId());
		assertNotNull(dbPet);
		assertEquals(pet.getName(), dbPet.getName());
	
	}

	@Test
	@Transactional
	@Rollback(true)
	public void TestPetType()
	{
		List<PetType> petTypes = getPetTypes();
		
		Pet pet=new Pet();
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jpaOwnerRepo.save(owner);
		
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
	
		Optional<PetType> dbPet = petTypes.stream().filter( dbPetType -> dbPetType.getId()== pet.getType().getId()).findAny();
		assertTrue(dbPet.isPresent());
		
	}
	
	private List<PetType> getPetTypes(){
		List<PetType> petTypes = petRepo.findPetTypes();
		assertNotNull(petTypes);
		assertFalse(petTypes.isEmpty());
		
		return petTypes;
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

	

}
