package org.springframework.samples.petclinic.service;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import org.junit.Before;
import org.junit.Test;
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
	
	
	@Before
	public void setUp()
	{
		petRepository=Mockito.mock(JpaPetRepositoryImpl.class);
		ownerRepository=Mockito.mock(JpaOwnerRepositoryImpl.class);
		vetRepository=Mockito.mock(JpaVetRepositoryImpl.class);
		visitRepository=Mockito.mock(JpaVisitRepositoryImpl.class);
		
		clinicServImpl=new ClinicServiceImpl(petRepository, vetRepository,ownerRepository, visitRepository);
//		new ClinicServiceImpl(petRepository, vetRepository, ownerRepository, visitRepository)
	}
	

	
	// Test cases for Owner 
	
	@Test
	public void testFindOwnerById()
	{
		Owner o= new Owner();
		o.setId(1);
		
		given(ownerRepository.findById(1)).willReturn(o);
		
		assertEquals(o, clinicServImpl.findOwnerById(1));
		verify(ownerRepository).findById(1);
	}

	
	@Test
	public void testFindOwnerByLastName()
	{
		Owner o= new Owner();
		o.setLastName("Daniel");
		List<Owner> list=new ArrayList<Owner>();
		
		list.add(o);
		
		given(ownerRepository.findByLastName("Daniel")).willReturn(list);
				
		assertEquals(1, clinicServImpl.findOwnerByLastName("Daniel").size());
		verify(ownerRepository).findByLastName("Daniel");
	}

	@Test
	public void saveOwner()
	{
		Owner o = new Owner();
		o.setFirstName("Jack");
		
		clinicServImpl.saveOwner(o);
		verify(ownerRepository).save(o);
	}

	
// Test cases for Pet 

	
	@Test
	public void savePet()
	{
		Pet p =new Pet();
		p.setName("Jack");
		clinicServImpl.savePet(p);
		verify(petRepository).save(p);
	}

	@Test
	public void testFindById()
	{
		Pet p= new Pet();
		p.setId(1);
		
		given(petRepository.findById(1)).willReturn(p);
		
		assertEquals(p, clinicServImpl.findPetById(1));
		verify(petRepository).findById(Mockito.anyInt());
	}
	
	@Test
	public void testFindPetTypes()
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
	
// Service for vet		
	@Test
	public void testFindVets()
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
	
// service for visit
	

	@Test
	public void testsaveVisit()
	{
		Visit v = new Visit();
		v.setId(1);
		
		clinicServImpl.saveVisit(v);
	
		verify(visitRepository).save(v);
	}
	
	@Test
	public void testFindVetsByPetId()
	{
		Pet pet=new Pet();
		pet.setId(1);
		
		Visit v= new Visit();
		v.setId(1);
		
		Collection<Visit> list=new ArrayList<Visit>();
		list.add(v);
		
		given(visitRepository.findByPetId(1)).willReturn((List<Visit>) list);
		
		int a=clinicServImpl.findVisitsByPetId(1).size();
		
		assertEquals(1, a);
		
		verify(visitRepository).findByPetId(1);
	}

	
}
