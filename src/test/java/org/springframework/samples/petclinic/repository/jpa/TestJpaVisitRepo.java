package org.springframework.samples.petclinic.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"classpath:spring/mvc-core-config.xml", 
		"classpath:spring/mvc-test-config.xml",
		"classpath:spring/business-config.xml",
		"classpath:spring/datasource-config.xml", 
		"classpath:spring/mvc-view-config.xml",
		"classpath:spring/tools-config.xml"})
public class TestJpaVisitRepo {

	@Autowired
    VisitRepository visitRepo;
    
	@Test
	@Transactional
	@Rollback(true)
	public void testSave()
	{
		Visit v= new Visit();
		Pet p=new Pet();
		p.setId(1);;
		v.setPet(p);
		visitRepo.save(v);
		assertEquals(v, visitRepo.findByPetId(1));
    }


    
	@Test
	@Transactional
	@Rollback(true)
	public void testFindPetById()
	{
		Visit v= new Visit();
		Pet p=new Pet();
		p.setId(1);
		v.setPet(p);
		visitRepo.save(v);
		
		
		assertEquals(1,visitRepo.findByPetId(1).size() );
	}


}
