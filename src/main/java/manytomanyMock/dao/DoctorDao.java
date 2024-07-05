package manytomanyMock.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import manytomanyMock.dto.Doctor;
import manytomanyMock.dto.Patient;

public class DoctorDao {
	public EntityManager getEntityManager()
	{
		return Persistence.createEntityManagerFactory("maha").createEntityManager();
		
	}
	public void saveDoctor(Doctor doctor)
	{ 
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(doctor);
		entityTransaction.commit();
	}
	public void updateDoctor(int id,Doctor doctor)
	{
		EntityManager entityManager=getEntityManager();
		Doctor doctordb=entityManager.find(Doctor.class,id);
		if(doctordb!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			doctor.setId(id);
			doctor.setPatient(doctordb.getPatient());
			entityManager.merge(doctor);
			entityTransaction.commit();
			
		}
		else {
			System.out.println("invalid student id");
		}
	}
	public void deleteDoctor(int id)
	{
		EntityManager entityManager=getEntityManager();
		Doctor doctordb=entityManager.find(Doctor.class,id);
		if(doctordb!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			List <Patient> patients=doctordb.getPatient();
			for(Patient patient:patients)
			{
				List <Doctor> doctors=patient.getDoctor();
				doctors.remove(doctordb);
				patient.setDoctor(doctors);
				
				
			}
			entityManager.remove(doctordb);
			entityTransaction.commit();
		
		}
		else {
			System.out.println("sorry id is not present");
		}
	}
	public void findDoctor(int id)
	{
		EntityManager entityManager=getEntityManager();
		Doctor doctordb=entityManager.find(Doctor.class,id);
		if(doctordb!=null)
		{
			System.out.println(doctordb);
		}
		else {
			System.out.println("sorry id is not present");
		}
	}
}
