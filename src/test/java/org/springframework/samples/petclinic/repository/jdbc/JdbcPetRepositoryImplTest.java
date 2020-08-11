package org.springframework.samples.petclinic.repository.jdbc;


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
@ActiveProfiles({"jdbc","HSQLDB"})
public class JdbcPetRepositoryImplTest 
{
	@Autowired
	PetRepository jdbcpetRepository;
	
	@Autowired
	JdbcOwnerRepositoryImpl jdbcOwnerRepository;
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void TestfindById()
	{
		final String PET_NAME="George";
		List<PetType> petTypes = jdbcpetRepository.findPetTypes();
		
		Pet pet=new Pet();
		pet.setId(6);
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
	
		Pet expectedPet = jdbcpetRepository.findById(pet.getId());
		assertNotNull(expectedPet);
		assertEquals(pet.getName(), expectedPet.getName());
	
	}
	
	private Owner getOwnerObject() {
		Owner owner=new Owner();
		owner.setId(11);
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");
		
		return owner;
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Rollback(true)
	@Test
	public void TestPetType()
	{
        final String PET_NAME="George";	
		List<PetType> petTypes = jdbcpetRepository.findPetTypes();
		
		
		Pet pet=new Pet();
		pet.setId(6);
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(3));
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
	
		List<PetType> pets = jdbcpetRepository.findPetTypes();
		System.out.println(pets);
		assertNotNull(pets);
		assertEquals("hamster", jdbcpetRepository.findPetTypes().get(3).getName());
		//System.out.println("---------***-------"+jdbcpetRepository.findPetTypes().get(4));
		assertEquals(6, jdbcpetRepository.findPetTypes().get(3).getId());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void TestSave()
	{
		final String PET_NAME="George";	
		List<PetType> petTypes = jdbcpetRepository.findPetTypes();
		System.out.println("---------------------------->> "+petTypes);
		
		Pet pet=new Pet();
		pet.setId(6);
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		pet.getOwner();
		Owner owner = getOwnerObject();
		owner.addPet(pet);
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner.getId());
		assertNotNull(owner.getPet(PET_NAME));
		Pet expectedPet = jdbcpetRepository.findById(pet.getId());
		assertNotNull(expectedPet);
		assertEquals(pet.getName(), expectedPet.getName());
	
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
