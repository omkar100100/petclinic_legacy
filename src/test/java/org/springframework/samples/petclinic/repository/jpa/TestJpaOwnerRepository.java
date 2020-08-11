package org.springframework.samples.petclinic.repository.jpa;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaOwnerRepository {

	@Autowired
	OwnerRepository ownerRepository;

	@Test
	@Transactional
	@Rollback(true)
	public void testFindById()
	{
		Owner owner = getOwnerObject();
		ownerRepository.save(owner);
		
		assertNotNull(owner.getId());
		assertNotNull(ownerRepository.findById(owner.getId()));
		assertEquals(owner.getLastName(), ownerRepository.findById(owner.getId()).getLastName());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSave()
	{
		Owner owner=getOwnerObject();
		
		ownerRepository.save(owner);
		assertEquals(owner.getLastName(), ownerRepository.findById(owner.getId()).getLastName());
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByLastName()
	{
		Owner owner = getOwnerObject();
		ownerRepository.save(owner);
		
		ArrayList<Owner> dbOwners =  (ArrayList<Owner>) ownerRepository.findByLastName(owner.getLastName());
		
		assertNotNull(dbOwners);
		assertFalse(dbOwners.isEmpty());
		assertTrue(dbOwners.contains(owner));
		
		Optional<Owner> searchOwner = dbOwners.stream().filter(dbOwner -> dbOwner.getId()== owner.getId()).findAny();
		assertTrue(searchOwner.isPresent());
		assertTrue(searchOwner.get().getLastName().equals(owner.getLastName()));
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
