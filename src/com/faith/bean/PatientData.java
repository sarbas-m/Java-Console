package com.faith.bean;

public class PatientData {

	private int appoinmentId;
	private int staffId;
	private int patientId;
	private int tokenNo;
	private String patientRegNo;
	private String patientName;
	private String patientGender;
	private String patientBloodGroup;

	public String getPatientRegNo() {
		return patientRegNo;
	}

	public String getPatientName() {
		return patientName;
	}



	public void setPatientRegNo(String patientRegNo) {
		this.patientRegNo = patientRegNo;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}




	public PatientData() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getAppoinmentId() {
		return appoinmentId;
	}

	public int getStaffId() {
		return staffId;
	}

	public int getTokenNo() {
		return tokenNo;
	}

	public void setAppoinmentId(int appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	public PatientData(int appoinmentId, int staffId, int patientId,
			int tokenNo, String patientRegNo, String patientName,
			String patientGender, String patientBloodGroup) {
		super();
		this.appoinmentId = appoinmentId;
		this.staffId = staffId;
		this.patientId = patientId;
		this.tokenNo = tokenNo;
		this.patientRegNo = patientRegNo;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientBloodGroup = patientBloodGroup;
	}

	
	public String toString() {
			
		return String.format("%-20s%-20s%-20s%-20s%n",this.patientRegNo,this.patientName,this.patientGender,this.patientBloodGroup);
	}



}
