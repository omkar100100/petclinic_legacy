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
//@ContextConfiguration(locations = {"classpath:spring/mvc-core-config.xml", 
//		"classpath:spring/mvc-test-config.xml",
//		"classpath:spring/business-config.xml",
//		"classpath:spring/datasource-config.xml", 
//		"classpath:spring/mvc-view-config.xml",
//		"classpath:spring/tools-config.xml"})
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
		Owner o=new Owner();
		o.setId(1);
		o.setLastName("David");
		o.setFirstName("Test");
		o.setAddress("test");
		o.setCity("test");
		o.setTelephone("1234567899");
		
		jpaOwnerRepo.save(o);
		
		//assertEquals("David", jpaOwnerRepo.findById(1).getLastName());
    
		given(query.getSingleResult()).willReturn(o);
		assertEquals("David", jpaOwnerRepo.findById(1).getLastName());
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
