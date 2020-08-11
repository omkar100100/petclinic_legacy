package org.springframework.samples.petclinic.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.jpa.JpaOwnerRepositoryImpl;
import org.springframework.samples.petclinic.repository.jpa.JpaPetRepositoryImpl;
import org.springframework.samples.petclinic.repository.jpa.JpaVetRepositoryImpl;
import org.springframework.samples.petclinic.repository.jpa.JpaVisitRepositoryImpl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TestClinicServiceImpl {
	
	JpaPetRepositoryImpl petRepository;
	JpaOwnerRepositoryImpl ownerRepository;
	JpaVetRepositoryImpl vetRepository;
	JpaVisitRepositoryImpl visitRepository;
	
	ClinicServiceImpl clinicServImpl;
	
	
	@BeforeEach
	public void setUp()
	{
		petRepository=Mockito.mock(JpaPetRepositoryImpl.class);
		ownerRepository=Mockito.mock(JpaOwnerRepositoryImpl.class);
		vetRepository=Mockito.mock(JpaVetRepositoryImpl.class);
		visitRepository=Mockito.mock(JpaVisitRepositoryImpl.class);
		clinicServImpl=new ClinicServiceImpl(petRepository, vetRepository,ownerRepository, visitRepository);
	}
	
	protected Owner getOwner()
	{
		Owner o= new Owner();
		o.setId(1);
		o.setFirstName("Jack");
		
		o.setLastName("Daniel");
		return o;
	}
	
	@Test
	void testFindOwnerById()
	{
		Owner o= getOwner();
		
		given(ownerRepository.findById(1)).willReturn(o);
		
		assertEquals(o, clinicServImpl.findOwnerById(o.getId()));
		verify(ownerRepository).findById(o.getId());
	}

	
	@Test
	void testFindOwnerByLastName()
	{
		Owner o= getOwner();
		List<Owner> list=new ArrayList<Owner>();
		
		list.add(o);
		
		given(ownerRepository.findByLastName("Daniel")).willReturn(list);
				
		assertEquals(1, clinicServImpl.findOwnerByLastName(o.getLastName()).size());
		verify(ownerRepository).findByLastName(o.getLastName());
	}

	@Test
	void saveOwner()
	{
		Owner o = getOwner();

		clinicServImpl.saveOwner(o);
		verify(ownerRepository).save(o);
	}

	protected Pet getPet()
	{
		Pet p= new Pet();
		p.setId(1);
		p.setName("Jack");
		return p;
	}
	
	@Test
	void savePet()
	{
		Pet p =getPet();
		clinicServImpl.savePet(p);
		verify(petRepository).save(p);
	}

	@Test
	void testFindById()
	{
		Pet p= getPet();
		
		given(petRepository.findById(1)).willReturn(p);
		assertEquals(p, clinicServImpl.findPetById(p.getId()));
		verify(petRepository).findById(Mockito.anyInt());
	}
	
	@Test
	void testFindPetTypes()
	{
		List<PetType> list= new ArrayList<PetType>();
		
		list.add(new PetType());
		list.add(new PetType());
		list.add(new PetType());
	
		given(petRepository.findPetTypes()).willReturn(list);
		
		Collection<PetType> list1= clinicServImpl.findPetTypes();
		assertEquals(3,list1.size());
		Mockito.verify(petRepository).findPetTypes();
	}
	

	@Test
	void testFindVets()
	{
		Vet v= new Vet();
		v.setFirstName("v");
		Vet v2= new Vet();
		v2.setFirstName("v2");
		
		Collection<Vet> list=new ArrayList<Vet>();
		list.add(v);
		list.add(v2);
		given(vetRepository.findAll()).willReturn(list);
		int a=clinicServImpl.findVets().size();
		assertEquals(2, a);
		verify(vetRepository).findAll();
	}

	
	@Test
	void testsaveVisit()
	{
		Visit v = new Visit();
		v.setId(1);
		clinicServImpl.saveVisit(v);
		verify(visitRepository).save(v);
	}
	
	@Test
	void testFindVetsByPetId()
	{
		Pet pet=getPet();
		Visit v= new Visit();
		v.setId(1);
		Collection<Visit> list=new ArrayList<Visit>();
		list.add(v);
		given(visitRepository.findByPetId(pet.getId())).willReturn((List<Visit>) list);
		int a=clinicServImpl.findVisitsByPetId(pet.getId()).size();
		assertEquals(1, a);
		verify(visitRepository).findByPetId(pet.getId());
	}

	
}
