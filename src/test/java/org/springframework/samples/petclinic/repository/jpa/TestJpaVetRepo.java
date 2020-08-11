package org.springframework.samples.petclinic.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaVetRepo {

	@Autowired
	VetRepository vetRepo;
	

	@Test
	@Transactional
	@Rollback(true)
	public void TestFindAll()
	{
		Collection<Vet> vetCollection = vetRepo.findAll();
		List<Vet> dbVetList = vetCollection.stream().collect(Collectors.toList());
		assertNotNull(dbVetList);
		assertFalse(dbVetList.isEmpty());
		
	}

}
