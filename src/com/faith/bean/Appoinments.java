package com.faith.bean;

public class Appoinments {
	private int appoinmentId;
	private int staffId;
	private int patientId;
	private int tokenNo;
	private String patientRegNo;
	private String patientName;
	private String patientGender;
	private String patientBloodGroup;
	private String patientMobile;

	public Appoinments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appoinments(int appoinmentId, int staffId, int patientId,
			int tokenNo, String patientRegNo, String patientName,
			String patientGender, String patientBloodGroup, String patientMobile) {
		
		this.appoinmentId = appoinmentId;
		this.staffId = staffId;
		this.patientId = patientId;
		this.tokenNo = tokenNo;
		this.patientRegNo = patientRegNo;
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientBloodGroup = patientBloodGroup;
		this.patientMobile = patientMobile;
	}

	public Appoinments(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public int getTokenNo() {
		return tokenNo;
	}

	public String getPatientRegNo() {
		return patientRegNo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public void setPatientRegNo(String patientRegNo) {
		this.patientRegNo = patientRegNo;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getPatientGender() {
		return patientGender;
	}

	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}

	public String getPatientMobile() {
		return patientMobile;
	}

	public void setAppoinmentId(int appoinmentId) {
		this.appoinmentId = appoinmentId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}

	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}

	
	public String toString() {

		return String.format("%-20s%-20s%-20s%-20s%-25s%n",this.tokenNo,this.patientRegNo,this.patientName,this.patientGender,this.patientMobile);
				
	}


}
