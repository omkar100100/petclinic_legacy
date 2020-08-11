package org.springframework.samples.petclinic.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations= {"classpath:spring/business-config.xml","classpath:spring/mvc-test-config.xml"})
@ActiveProfiles({"jpa","HSQLDB"})
public class TestJpaVisitRepo {

	@Autowired
    VisitRepository visitRepo;

	@Autowired
	PetRepository petRepo;
	
	@Autowired
	OwnerRepository ownerRepo;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveVisit()
	{
		Visit visit= getVisitObject();
		visitRepo.save(visit);
		assertNotNull(visit.getId());
		
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testFindByPetById()
	{
		Visit visit= getVisitObject();
		visitRepo.save(visit);

		Collection<Visit> dbVisitsCollection =visitRepo.findByPetId(visit.getPet().getId());
		List<Visit> dbPetVisits = dbVisitsCollection.stream().collect(Collectors.toList());
		assertNotNull(dbPetVisits);
		assertFalse(dbPetVisits.isEmpty());
		
		
	}
	
	private Visit getVisitObject() {

		final String PET_NAME="PINKY";
		List<PetType> petTypes = petRepo.findPetTypes();
		Pet pet=new Pet();
		pet.setName(PET_NAME);
		pet.setType(petTypes.get(2));
		
		Owner owner=new Owner();
		owner.setLastName("David");
		owner.setFirstName("Test");
		owner.setAddress("test");
		owner.setCity("test");
		owner.setTelephone("1234567899");
		owner.addPet(pet);
		ownerRepo.save(owner);
		
		
		Visit visit= new Visit();
		LocalDate date = LocalDate.now(); 
		
		visit.setDate(date);
		visit.setDescription("Visit_now");
		visit.setPet(pet);
		return visit;
	}

}
