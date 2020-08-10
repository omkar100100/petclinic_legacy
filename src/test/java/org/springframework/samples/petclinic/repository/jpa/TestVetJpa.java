package org.springframework.samples.petclinic.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


//@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaVetRepo {

	@Autowired
	VetRepository vetRepo;
	

	@Test
	@Transactional
	@Rollback(true)
	public void TestPetType()
	{
		Collection<Vet> v=vetRepo.findAll();
		ArrayList<Vet> newList = new ArrayList<>(v);

		assertEquals(6, newList.size());
		assertEquals("James",newList.get(0).getFirstName());
		assertEquals("Carter",newList.get(0).getLastName());
		assertEquals(new Integer(1),newList.get(0).getId());
		assertEquals("Linda",newList.get(1).getFirstName());
		assertEquals("Douglas",newList.get(1).getLastName());
		assertEquals(new Integer(3),newList.get(1).getId());

	}

}
