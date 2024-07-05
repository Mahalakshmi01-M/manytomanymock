package manytomanyMock.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import manytomanyMock.dto.Doctor;
import manytomanyMock.dto.Patient;

public class PatientDao {
	public EntityManager getEntityManager()
	{
		return Persistence.createEntityManagerFactory("maha").createEntityManager();
		
	}
	public void savePatient(int id,Patient patient)
	{
		EntityManager entityManager=getEntityManager();
		Doctor doctordb=entityManager.find(Doctor.class,id);
		if(doctordb!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			List <Patient> patients=doctordb.getPatient();
			patients.add(patient);
			doctordb.setPatient(patients);
			List <Doctor>doctors=new ArrayList<Doctor>();
			doctors.add(doctordb);
			patient.setDoctor(doctors);
			entityManager.persist(patient);
			entityManager.merge(doctordb);
			entityTransaction.commit();
		}
		else {
			System.out.println("invalid doctor id");
		}
	}
	public void updatePatient(int id,Patient patient)
	{
		EntityManager entityManager=getEntityManager();
		Patient patientdb=entityManager.find(Patient.class,id);
		if(patientdb!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			patient.setId(id);
			patient.setDoctor(patientdb.getDoctor());
			entityManager.merge(patient);
			entityTransaction.commit();
			
		}
		else {
			System.out.println("Sorry id is not present");
		}
	}
	public void deletePatinet(int patientId)
	{
		EntityManager entityManager=getEntityManager();
		Patient patientdb=entityManager.find(Patient.class,patientId);
		if(patientdb!=null)
		{
			EntityTransaction entityTransaction=entityManager.getTransaction();
			entityTransaction.begin();
			List <Doctor> doctors=patientdb.getDoctor();
			for(Doctor doctor:doctors)
			{
				List <Patient> patients=doctor.getPatient();
				patients.remove(doctor);
				doctor.setPatient(patients);
			}
			entityManager.remove(patientdb);
			entityTransaction.commit();
			
			
		}
		else {
			System.out.println("sorry id is not present");
		}
	}
	public void findPatinet(int id)
	{
		EntityManager entityManager=getEntityManager();
		Patient patientdb=entityManager.find(Patient.class,id);
		if(patientdb!=null)
		{
			System.out.println(patientdb);
			
			
		}
		else {
			System.out.println("sorry id is not present");
		}
	}
}
