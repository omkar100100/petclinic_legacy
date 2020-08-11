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
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jdbc","HSQLDB"})
public class TestJdbcVisitRepository
{
	@Autowired
    VisitRepository visitRepo;

	@Autowired
	PetRepository petRepo;
	


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
	  visitRepo.save(visit);
	  petRepo.save(pet);
	  pet = this.petRepo.findById(7);
	  assertThat(pet.getVisits().size()).isEqualTo(found + 1);
	  assertThat(visit.getId()).isNotNull();
	 }
}
