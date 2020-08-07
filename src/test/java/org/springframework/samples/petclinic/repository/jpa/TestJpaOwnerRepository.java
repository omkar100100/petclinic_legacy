package org.springframework.samples.petclinic.repository.jpa;


import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@RunWith(SpringJUnit4ClassRunner.class)
@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaOwnerRepository {

	
	
	@Autowired
	OwnerRepository ownerRepository;
	
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindById1() {
		Owner owner = ownerRepository.findById(1);
		
		assertTrue(owner!=null);
		assertTrue(owner.getFirstName().equals("George"));
		assertTrue(owner.getLastName().equals("Franklin"));
		assertTrue(owner.getCity().equals("Madison"));
		assertTrue(owner.getTelephone().equals("6085551023"));
		assertTrue(owner.getAddress().equals("110 W. Liberty St."));		
		
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testSave()
	{
		Owner o=new Owner();
		o.setId(1);
		o.setLastName("David");
		o.setFirstName("Test");
		o.setAddress("test");
		o.setCity("test");
		o.setTelephone("1234567899");
		ownerRepository.save(o);
		assertEquals("David", ownerRepository.findById(1).getLastName());
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByLastName()
	{
		ArrayList<Owner> owner2 =  (ArrayList<Owner>) ownerRepository.findByLastName("Franklin");
		assertTrue(owner2.get(0).getFirstName().equals("George"));
		assertTrue(owner2.get(0).getLastName().equals("Franklin"));
		assertTrue(owner2.get(0).getCity().equals("Madison"));
		assertTrue(owner2.get(0).getTelephone().equals("6085551023"));
		assertTrue(owner2.get(0).getAddress().equals("110 W. Liberty St."));		
		
    }


}
