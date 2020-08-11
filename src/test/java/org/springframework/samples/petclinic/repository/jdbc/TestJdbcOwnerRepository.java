package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/business-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class TestJdbcOwnerRepository
{
	@Autowired
	JdbcOwnerRepositoryImpl  jdbcOwnerRepository;
	
	@Transactional
	@Rollback(true)	
	@Test
	public void testFindByLastName() {
		//Saving
		Owner owner = getOwnerObject();
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner);
		assertNotNull(owner.getId());
		
		//FINDING
		Owner dbOwner = jdbcOwnerRepository.findById(owner.getId());
		assertNotNull(dbOwner);
		assertEquals(owner.getLastName(), dbOwner.getLastName());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testFindById()
	{
		//Saving
				Owner owner = getOwnerObject();
				System.out.println("------------------"+owner);
				jdbcOwnerRepository.save(owner);
				assertNotNull(owner);
				assertNotNull(owner.getId());
		//FINDING
				Owner dbOwner = jdbcOwnerRepository.findById(owner.getId());
				assertNotNull(dbOwner);
				System.out.println(owner.getId());
				assertEquals(owner.getId(), dbOwner.getId());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testSave()
	{
     Owner o=getOwnerObject();
     jdbcOwnerRepository.save(o);
     assertEquals("1234567899", jdbcOwnerRepository.findById(o.getId()).getTelephone());
	}
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testGetPetTypes()
	{
		//Saving
		Owner owner = getOwnerObject();
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner);
		assertNotNull(owner.getId());
    //FINDING
		Collection<PetType> petType = new ArrayList<PetType>();
		petType = jdbcOwnerRepository.getPetTypes();
		assertNotNull(petType);
		assertEquals("lizard", ((ArrayList<PetType>) petType).get(4).toString());
}

	@Transactional
	@Rollback(true)
	@Test
	public void testLoadPetsAndVisits()
	{
		
	}
	

	
	private Owner getOwnerObject() 
	{
		Owner owner=new Owner();
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");

		return owner;
	}

}
