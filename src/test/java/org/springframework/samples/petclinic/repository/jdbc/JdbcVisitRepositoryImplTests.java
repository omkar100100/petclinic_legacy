package org.springframework.samples.petclinic.repository.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petclinic.model.Visit;

public class JdbcVisitRepositoryImplTests 
{
  DataSource dataSource;
  List<Visit> visit;
	
	@BeforeEach
	void setUp()
	{
	   dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
			      .addScript("classpath:db/hsqldb/initDB.sql")
			      .addScript("classpath:db/hsqldb/populateDB.sql")
			      .build();
	}
	
	@Test
	public void findByPetId()
	{
		visit = new ArrayList<Visit>();
		JdbcVisitRepositoryImpl jdbcVisitDao = new JdbcVisitRepositoryImpl(dataSource);
		visit = jdbcVisitDao.findByPetId(7);
		//System.out.println(visit.get(0).getId());
		assertEquals(1,visit.get(0).getId());
	}
	
	
}
