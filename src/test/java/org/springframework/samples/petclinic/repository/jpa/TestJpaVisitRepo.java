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

@ContextConfiguration(locations = 
{"classpath:spring/business-config.xml",
		"classpath:spring/mvc-test-config.xml"})
@ActiveProfiles("jpa")
public class TestJpaVisitRepo {

	@Autowired
    VisitRepository visitRepo;
    	@Test
	@Transactional
	@Rollback(true)
	public void testSave()
	{
		
		Pet p=new Pet();		
		LocalDate date = LocalDate.now();  
		Visit v= new Visit();
		v.setPet(p);
		v.setDate(date);
		v.setDescription("Something");
		visitRepo.save(v);
		Integer a= new Integer(20);
		assertEquals(v, visitRepo.findByPetId(a));
    }


    
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPetById()
	{
		Integer a= new Integer(7);
		
		Collection<Visit> v=visitRepo.findByPetId(a);
		ArrayList<Visit> newList = new ArrayList<>(v);
	
		assertEquals(2, v.size());
		assertEquals("rabies shot", newList.get(0).getDescription());
		assertEquals(new Integer(1), newList.get(0).getId());
		assertEquals("spayed", newList.get(1).getDescription());
		assertEquals(new Integer(4), newList.get(1).getId());
	}


}
