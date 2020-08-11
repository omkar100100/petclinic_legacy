package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.samples.petclinic.repository.VetRepository;


@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/business-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class JdbcVetRepositoryIplTest
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
	 // assertEquals(expected, actual);
	}
	
}