package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;


public class JdbcPetRepositoryImplTest 
{
	DataSource dataSource;
	
	@Autowired
	OwnerRepository ownerRepository;
	
	@BeforeEach
	void setUp()
	{
	   dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
			      .addScript("classpath:db/hsqldb/initDB.sql")
			      .addScript("classpath:db/hsqldb/populateDB.sql")
			      .build();
	}
	
	@Test
	public void findPetTypesTest() throws Exception
	{
		List<PetType> list = new ArrayList<PetType>();
		JdbcPetRepositoryImpl jdbcPetDao = new JdbcPetRepositoryImpl(dataSource,ownerRepository);
		list = (List<PetType>) jdbcPetDao.findPetTypes();
		//System.out.println(list);
		assertEquals(6,list.size());
	}
	
	/*@Test
	public void findByIdTest()
	{
	    Pet p = new Pet();
		//List<Pet> list = new ArrayList<Pet>();
		JdbcPetRepositoryImpl jdbcPetDao = new JdbcPetRepositoryImpl(dataSource,ownerRepository);
	     p = jdbcPetDao.findById(1);
		System.out.println(jdbcPetDao.findById(1)+"    hh       "+p);
		//assertEquals(6,list.size());
	}*/
	
	}
