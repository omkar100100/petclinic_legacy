package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/business-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class TestJdbcVetRepository
{
	@Autowired
	public JdbcVetRepositoryImpl jdbcVetRespository;
	
	@Transactional
	@Rollback(true)	
	@Test
	public void testFindAll() 
	{
	  Collection<Vet> vets = new ArrayList<Vet>();
	  vets = jdbcVetRespository.findAll();
	  System.out.println(vets);
	  assertNotNull(vets);
	  assertFalse(vets.isEmpty());
	 // assertEquals(expected, actual);
	}
}
