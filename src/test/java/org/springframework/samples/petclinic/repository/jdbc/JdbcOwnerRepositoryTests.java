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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;


@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/business-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class JdbcOwnerRepositoryTests 
{

	@Autowired
	JdbcOwnerRepositoryImpl  jdbcOwnerRepository;
	
		
	@Test
	public void testFindByLastName() {
		//Saving
		Owner owner = getOwnerObject();
		jdbcOwnerRepository.save(owner);
		assertNotNull(owner);
		assertNotNull(owner.getId());
		
		//FINDING
		Owner dbOwner = jdbcOwnerRepository.findById(owner.getId());
		assertNotNull(dbOwner);
		assertEquals(owner.getLastName(), dbOwner.getLastName());
	}
	
	private Owner getOwnerObject() {
		Owner owner=new Owner();
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");

		return owner;
	}

	
	
	
//	@BeforeEach
//	void setUp()
//	{
//	dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
//			      .addScript("classpath:db/hsqldb/initDB.sql")
//			      .addScript("classpath:db/hsqldb/populateDB.sql")
//			      .build();
//	}
//
//	@Test
//	public void testFindByLastName() throws Exception
//	{
//		  JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
//		  List<Owner> owners = (List<Owner>) jdbcOwnerDao.findByLastName("Coleman");
//		  System.out.println(owners.get(0).getFirstName());
//		  assertEquals("Jean",owners.get(0).getFirstName());
//		//  assertEquals("Davis", jdbcOwnerDao.findByLastName("Davis"));
//	}
//	
//	@Test
//	public void testFindById() throws Exception
//	{
//		JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
//		  Owner owners = jdbcOwnerDao.findById(1);
//		  assertEquals("George",owners.getFirstName());
//	}
//	
//
//	@Test
//	public void testgetPetTypes() throws Exception
//	{
//		List<PetType> list = new ArrayList<PetType>();
//		JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
//		list = (List<PetType>) jdbcOwnerDao.getPetTypes();
//		System.out.println(list);
//		assertEquals(6,list.size());
//		
//	}
//
//	@Test
//	public void loadPetsAndVisits()
//	{
//		  JdbcOwnerRepositoryImpl jdbcOwnerDao = new JdbcOwnerRepositoryImpl(dataSource);
//		  Owner owners = jdbcOwnerDao.findById(1);
//		 // System.out.println(owners);
//		  owners.setFirstName("Philip");
//		  owners.setCity("Hyderabad");
//		 // System.out.println(owners);
//		  jdbcOwnerDao.save(owners);
//		  Owner owners1 = jdbcOwnerDao.findById(1);
//        //  System.out.println(owners1);
//		  assertEquals("Philip",owners1.getFirstName());
//		 
//	}
}