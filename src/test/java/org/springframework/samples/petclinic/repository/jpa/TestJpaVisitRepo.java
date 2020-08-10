package org.springframework.samples.petclinic.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaVisitRepo {

	@Autowired
    VisitRepository visitRepo;

    
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
