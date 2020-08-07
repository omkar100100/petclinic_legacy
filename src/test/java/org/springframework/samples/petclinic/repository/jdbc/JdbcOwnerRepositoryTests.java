package org.springframework.samples.petclinic.repository.jdbc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.model.Owner;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
public class JdbcOwnerRepositoryTests 
{
	@Mock 
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Mock
	SimpleJdbcInsert simpleJdbcInsert;
	
	//@Autowired
	JdbcOwnerRepositoryImpl  jdbcOwnerRepositoryImpl;
	
	private MockMvc mockMvc;
	
	@Autowired
    protected ClinicService clinicService;
	
	DataSource dataSource;
	//@Autowired
	Owner owner;
	
	@Mock
    JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp()
	{
	dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
			      .addScript("classpath:db/hsqldb/initDB.sql")
			      .addScript("classpath:db/hsqldb/populateDB.sql")
			      .build();
	}
	
	@Test
	public void testFindByLastName() throws Exception
	{
		  JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
		  List<Owner> owners = (List<Owner>) jdbcOwnerDao.findByLastName("Coleman");
		  System.out.println(owners.get(0).getFirstName());
		  assertEquals("Jean",owners.get(0).getFirstName());
		//  assertEquals("Davis", jdbcOwnerDao.findByLastName("Davis"));
	}
	
	@Test
	public void testFindById() throws Exception
	{
		JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
		  Owner owners = jdbcOwnerDao.findById(1);
		  assertEquals("George",owners.getFirstName());
	}
	
	@Test
	public void testgetPetTypes() throws Exception
	{
		List<PetType> list = new ArrayList<PetType>();
		JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
		list = (List<PetType>) jdbcOwnerDao.getPetTypes();
		System.out.println(list);
		assertEquals(6,list.size());
		
	}
	
	
	@Test
	public void loadPetsAndVisits()
	{
		  JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
		  Owner owners = jdbcOwnerDao.findById(1);
		 // System.out.println(owners);
		  owners.setFirstName("Philip");
		  owners.setCity("Hyderabad");
		 // System.out.println(owners);
		  jdbcOwnerDao.save(owners);
		  Owner owners1 = jdbcOwnerDao.findById(1);
        //  System.out.println(owners1);
		  assertEquals("Philip",owners1.getFirstName());
		 
	}
}