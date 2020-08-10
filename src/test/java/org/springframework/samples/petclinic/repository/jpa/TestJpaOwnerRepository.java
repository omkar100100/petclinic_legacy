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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaOwnerRepository {

	@Autowired
	OwnerRepository ownerRepository;

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
		Owner o=getOwnerObject();
		
		ownerRepository.save(o);
		assertEquals("David", ownerRepository.findById(o.getId()).getLastName());
	}

	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByLastName()
	{
		Owner owner = getOwnerObject();
		ownerRepository.save(owner);
		
		ArrayList<Owner> owner2 =  (ArrayList<Owner>) ownerRepository.findByLastName(owner.getLastName());
		
		assertTrue(owner2.get(0).getLastName().equals(owner.getLastName()));
    }
}
