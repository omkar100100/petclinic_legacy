package org.springframework.samples.petclinic.repository.jpa;


import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaOwnerRepository {


	@Autowired
	OwnerRepository jpaOwnerRepo;
    
    Query query=mock(Query.class);

	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindById()
	{
		Owner owner = getOwnerObject();
		jpaOwnerRepo.save(owner);
		
		assertNotNull(owner.getId());
		assertNotNull(jpaOwnerRepo.findById(owner.getId()));
		assertEquals(owner.getLastName(), jpaOwnerRepo.findById(owner.getId()).getLastName());
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
//	@Transactional
//	@Rollback(true)
//	public void testFindByLastName()
//	{
//		Owner o=new Owner();
//		o.setId(1);
//		o.setLastName("David");
//		jpaOwnerRepo.save(o);
//		
//		Collection<Owner> c= jpaOwnerRepo.findByLastName("David");
//		
//		assertEquals(1, c.size());
//    }
//
//	
//	@Test
//	@Transactional
//	@Rollback(true)
//	public void testSave()
//	{
//		Owner o=new Owner();
//		o.setId(1);
//		o.setLastName("David");
//		jpaOwnerRepo.save(o);
//		
//		Owner o1= jpaOwnerRepo.findById(1);
//		
//		assertEquals("David", jpaOwnerRepo.findById(1).getLastName());
//    }


}
