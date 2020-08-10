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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
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
