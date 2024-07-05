package manytomanyMock.cont;

import manytomanyMock.dao.DoctorDao;
import manytomanyMock.dao.PatientDao;
import manytomanyMock.dto.Doctor;
import manytomanyMock.dto.Patient;

public class PatientDoctorCont {
public static void main(String[] args) {
	Doctor doctor=new Doctor();
	doctor.setId(1);
	doctor.setName("maha");
	doctor.setFees(20000);
	
	DoctorDao doctordao=new DoctorDao();
	doctordao.saveDoctor(doctor);
	
	Patient patient =new Patient();
	patient.setId(101);
	patient.setName("kumar");
	patient.setAddress("bangalore");
	
	PatientDao patientDao=new PatientDao();
	patientDao.savePatient(1, patient);
	
	
}
}
