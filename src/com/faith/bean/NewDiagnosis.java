package com.faith.bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewDiagnosis {
	
	private int prescriptionId;
	private int patientId;
	private String diagnos;
	private String notes;
	private int staffId;
	private int appoinmentId;
	private int medicineId;
	private int doasageId;
	private int daysId;
	private int testId;
	private Date createdOn;
	private String testName;
	private String medicineName;
	private String staffName;
	private String specialization;
	private String dosage;
	private int days;
	private String lowRange;
	private String highRange;
	private String actualValue;
	private String doctorNotes;
	
	
	

	public NewDiagnosis(String staffName,
			String specialization, String doctorNotes,Date createdOn) {
		super();
		this.createdOn = createdOn;
		this.staffName = staffName;
		this.specialization = specialization;
		this.doctorNotes = doctorNotes;
	}
	public NewDiagnosis(Date createdOn, String testName, String staffName,
			String specialization, String lowRange, String highRange,
			String actualValue) {
		super();
		this.createdOn = createdOn;
		this.testName = testName;
		this.staffName = staffName;
		this.specialization = specialization;
		this.lowRange = lowRange;
		this.highRange = highRange;
		this.actualValue = actualValue;
	}
	public NewDiagnosis(Date createdOn, String testName, String lowRange,
			String highRange, String actualValue) {
		super();
		this.createdOn = createdOn;
		this.testName = testName;
		this.lowRange = lowRange;
		this.highRange = highRange;
		this.actualValue = actualValue;
	}
	public NewDiagnosis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPrescriptionId() {
		return prescriptionId;
	}
	public int getPatientId() {
		return patientId;
	}
	public String getDiagnos() {
		return diagnos;
	}
	public String getNotes() {
		return notes;
	}
	public int getStaffId() {
		return staffId;
	}
	public int getAppoinmentId() {
		return appoinmentId;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public int getDoasageId() {
		return doasageId;
	}
	public int getDaysId() {
		return daysId;
	}
	public int getTestId() {
		return testId;
	}
	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setDiagnos(String diagnos) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    Pattern patOne = Pattern.compile("^(?!\\s)[a-zA-Z0-9,./ ]*$");
	    while(true){
		Matcher matcher = patOne.matcher(diagnos);
		boolean finder = matcher.find();//false
		
		if(!finder){
			System.out.println("Invalid ");
			diagnos=br.readLine();
		}else{
			this.diagnos = diagnos;
			break;
		}
	    }
		
	}
	public void setNotes(String notes) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    Pattern patOne = Pattern.compile("^(?!\\s)[a-zA-Z0-9,./ ]*$");
	    while(true){
		Matcher matcher = patOne.matcher(notes);
		boolean finder = matcher.find();//false
		
		if(!finder){
			System.out.println("Invalid");
			notes=br.readLine();
		}else{
			this.notes = notes;
			break;
		}
	    }
		
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public void setAppoinmentId(int appoinmentId) {
		this.appoinmentId = appoinmentId;
	}
	public int setMedicineId(String medicineId) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x=0;
	    Pattern patOne = Pattern.compile("\\d+");
	    while(true){
		Matcher matcher = patOne.matcher(medicineId);
		boolean finder = matcher.find();//false
		
		if(!finder){
			System.out.println("Select medicine option from above");
			medicineId=br.readLine();
		}else if(finder){
			int id=Integer.parseInt(medicineId);
			if(id==10){
				x=id;
				break;
			}
			else if(id>0&&id<10){
				System.out.println(id);
				this.medicineId = id;
				break;
			}else{
				System.out.println("Select medicine option from above");
				medicineId=br.readLine();
			}
		}
	    }
		return x;
		
	}
	public void setDoasageId(String doasageId) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		    Pattern patOne = Pattern.compile("\\d+");
		    while(true){
			Matcher matcher = patOne.matcher(doasageId);
			boolean finder = matcher.find();//false
			
			if(!finder){
				System.out.println("Select dosage option from above");
				doasageId=br.readLine();
			}else if(finder){
				int id=Integer.parseInt(doasageId);
				if(id>0&&id<8){
					System.out.println(id);
					this.doasageId = id;
					break;
				}else{
					System.out.println("Select dosage option from above");
					doasageId=br.readLine();
				}
			}
		    }
		
	}
	public void setDaysId(int daysId) {
		this.daysId = daysId;
	}
	public int setTestId(String testId) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x=0;
	    Pattern patOne = Pattern.compile("\\d+");
	    while(true){
		Matcher matcher = patOne.matcher(testId);
		boolean finder = matcher.find();//false
		
		if(!finder){
			System.out.println("Select test option from above");
			testId=br.readLine();
		}else if(finder){
			
			int id=Integer.parseInt(testId);
			if(id==10){
				x=id;
				break;
			}
			if(id>0&&id<10){
				System.out.println(id);
				this.testId = id;
				
				break;
			}else{
				System.out.println("Select test option from above");
				testId=br.readLine();
			}
		}
	    }
		return x;
	}

	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public String getTestName() {
		return testName;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	
	public NewDiagnosis(int prescriptionId, int patientId, String diagnos,
			String notes, int staffId, int appoinmentId, int medicineId,
			int doasageId, int daysId, int testId, Date createdOn,
			String testName, String medicineName, String staffName,
			String dosage, int days) {
		super();
		this.prescriptionId = prescriptionId;
		this.patientId = patientId;
		this.diagnos = diagnos;
		this.notes = notes;
		this.staffId = staffId;
		this.appoinmentId = appoinmentId;
		this.medicineId = medicineId;
		this.doasageId = doasageId;
		this.daysId = daysId;
		this.testId = testId;
		this.createdOn = createdOn;
		this.testName = testName;
		this.medicineName = medicineName;
		this.staffName = staffName;
		this.dosage = dosage;
		this.days = days;
	}
	

public NewDiagnosis(String diagnos, String notes, Date createdOn,
		String testName, String medicineName, String dosage, int days) {
	super();
	this.diagnos = diagnos;
	this.notes = notes;
	this.createdOn = createdOn;
	this.testName = testName;
	this.medicineName = medicineName;
	this.dosage = dosage;
	this.days = days;
}

/*public String toString() {

	return String.format("%-20s%-20s%-25s%-20s%-15s%-15s%-20s%n",this.diagnos,this.notes,this.testName,this.medicineName,this.dosage,this.days,this.createdOn);

}*/
public NewDiagnosis(String diagnos, String notes, Date createdOn) {
	super();
	this.diagnos = diagnos;
	this.notes = notes;
	this.createdOn = createdOn;
}

public String displayDiagnos(){

	return String.format("%-30s%-25s%-25s%-20s%n",this.diagnos,this.staffName,this.specialization,this.createdOn);
}
public String displayNotes(){

return String.format("%-30s%-20s%-20s%-20s%n",this.doctorNotes,this.staffName,this.specialization,this.createdOn);
	
	
}

public NewDiagnosis(Date createdOn, String medicineName, String staffName,
		String specialization, String dosage, int days) {
	super();
	this.createdOn = createdOn;
	this.medicineName = medicineName;
	this.staffName = staffName;
	this.specialization = specialization;
	this.dosage = dosage;
	this.days = days;
}
public NewDiagnosis(Date createdOn, String medicineName, String dosage,
		int days) {
	super();
	this.createdOn = createdOn;
	this.medicineName = medicineName;
	this.dosage = dosage;
	this.days = days;
}


public NewDiagnosis(Date createdOn, String testName, String staffName,
		String specialization) {
	super();
	this.createdOn = createdOn;
	this.testName = testName;
	this.staffName = staffName;
	this.specialization = specialization;
}
public String displayMedicine(){
	return String.format("%-20s%-10s%-10s%-20s%-20s%-20s%n",this.medicineName,this.dosage,this.days,this.staffName,this.specialization,this.createdOn);
}
public NewDiagnosis(Date createdOn, String testName) {
	super();
	this.createdOn = createdOn;
	
	this.testName = testName;
}
public String displayTest(){

	return String.format("%-30s%-20s%-20s%-20s%n",this.testName,this.staffName,this.specialization,this.createdOn);
}


public String displayReport(){
	
	return String.format("%-25s%-35s%-35s%-35s%-20s%-20s%-20s%n",this.testName,this.lowRange,this.highRange,this.actualValue,this.staffName,this.specialization,this.createdOn);
	
}

public NewDiagnosis(String diagnos, Date createdOn, String staffName,
		String specialization) {
	super();
	this.diagnos = diagnos;
	this.createdOn = createdOn;
	this.staffName = staffName;
	this.specialization = specialization;
}
public String getDosage() {
	return dosage;
}
public int getDays() {
	return days;
}
public void setDosage(String dosage) {
	this.dosage = dosage;
}
public void setDays(String days) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Pattern patOne = Pattern.compile("\\d+");
    while(true){
	Matcher matcher = patOne.matcher(days);
	boolean finder = matcher.find();//false
	
	if(!finder){
		System.out.println("Select days in positive numeric");
		days=br.readLine();
	}else if(finder) {
		   int id=Integer.parseInt(days);
		   if(id>0){

				this.days = id;
				break;   
		   }else{
			   System.out.println("Select days in positive numeric");
				days=br.readLine(); 
		   }


	}
    }
	
}
@Override
public String toString() {
	return "NewDiagnosis [diagnos=" + diagnos + ", notes=" + notes
			+ ", appoinmentId=" + appoinmentId + ", medicineId=" + medicineId
			+ ", doasageId=" + doasageId + ", testId=" + testId + ", days="
			+ days + "]";
}
public NewDiagnosis(String diagnos, Date createdOn) {
	super();
	this.diagnos = diagnos;
	this.createdOn = createdOn;
}



}
