package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.OwnerRepository;

public class JdbcVetRepositoryIplTest
{
	JdbcTemplate jdbcTemplate;
    DataSource dataSource;
	
	@Autowired
	JdbcVetRepositoryImpl jdbcVetRepositoryImpl;
	
	@BeforeEach
	void setUp()
	{
	   dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
			      .addScript("classpath:db/hsqldb/initDB.sql")
			      .addScript("classpath:db/hsqldb/populateDB.sql")
			      .build();
	   jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void findAll()
	{
	  Collection<Vet> vets = new ArrayList<Vet>();
	  JdbcVetRepositoryImpl jdbcVetDao = new JdbcVetRepositoryImpl(jdbcTemplate);
	  vets = jdbcVetDao.findAll();
    //  System.out.println(vets.getClass());
      assertEquals(6,vets.size());
	}

}
