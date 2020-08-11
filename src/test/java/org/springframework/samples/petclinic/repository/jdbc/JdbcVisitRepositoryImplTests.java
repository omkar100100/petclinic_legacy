package org.springframework.samples.petclinic.repository.jdbc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class JdbcVisitRepositoryImplTests {

	@Autowired
    VisitRepository visitRepo;

	@Autowired
	PetRepository petRepo;
	

/*
	private Visit getVisitObject() {

		final String PET_NAME="PINKY";

		Owner owner=new Owner();
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");
		
		List<PetType> petTypes = petRepo.findPetTypes();
		Pet pet=new Pet();
		pet.setName(PET_NAME);
		pet.setId(10);
		pet.setType(petTypes.get(2));
		Visit v= new Visit();
		LocalDate date = LocalDate.now(); 
		
		v.setDate(date);
		v.setDescription("Visit_now");
		v.setId(1);
		v.setPet(pet);
		return v;
	}
	*/
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPetById2()
	{
		List<Visit> visit = new ArrayList<Visit>();
		visit = visitRepo.findByPetId(7);
		//System.out.println(visit.get(0).getId());
		assertEquals(1,visit.get(0).getId());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveVisit2()
	{
	  Pet pet= petRepo.findById(7);
	  int found = pet.getVisits().size();
	  Visit visit = new Visit();
	  pet.addVisit(visit);
      visit.setDescription("normal");
	  //System.out.println("22222222222 "+visit.getDescription());
	  visitRepo.save(visit);
	  petRepo.save(pet);
	  pet = this.petRepo.findById(7);
	  assertThat(pet.getVisits().size()).isEqualTo(found + 1);
	  assertThat(visit.getId()).isNotNull();
	 }
	
}