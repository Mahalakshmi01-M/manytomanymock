package manytomanyMock.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Doctor {
	@Id
	private int id;
	private String name;
	private double fees;
	@ManyToMany
	private List<Patient> patient;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", fees=" + fees + ", patient=" + patient + "]";
	}
	
	
}
