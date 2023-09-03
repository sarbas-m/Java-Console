package com.faith.lib;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.faith.bean.Appoinments;
import com.faith.bean.NewDiagnosis;
import com.faith.bean.PatientData;
import com.faith.bean.User;
import com.faith.dao.CmsDaoImplementation;
import com.faith.dao.ICmsDaoService;

public class CmsMenu {
	static ICmsDaoService daoService = new CmsDaoImplementation();
	static NewDiagnosis newDiagnosis = new NewDiagnosis();
	// static List<NewDiagnosis> diagnosList = new ArrayList<NewDiagnosis>();
	static Scanner input = new Scanner(System.in);

	public static void menu() throws Exception {
		int roleId = 0;
		Scanner input = new Scanner(System.in);
		User user = new User();
		int i;

		while (true) {
			for (i = 0; i < 3; i++) {

				System.out
						.println("\n\n************************************WELCOME TO CLINICAL MANAGEMENT SYSTEM**********************"
								+ "*********************");
				System.out.println("\n\n\n\t\t\t\t\tEnter Your username:");
				user.setUserName(input.nextLine());
				System.out.println("\t\t\t\t\tEnter Your password:");
				user.setPassWord(input.nextLine());
				try {
					if (daoService.checkLogin(user)) {
						System.out.println("\nSuccessfully logged in\n");

						roleId = daoService.getUserIdFromDb(user);

						if (roleId == 1) {
							
							System.out.println("Welcome Admin "
									+ daoService.getUserNameFromDb(user));
							Admin(user);
							break;
						} else if (roleId == 2) {
							System.out.println("Welcome Receptionist "
									+ daoService.getUserNameFromDb(user));
							receptionist(user);
							break;
						} else if (roleId == 3) {
							System.out.println("Welcome Dr."
									+ daoService.getUserNameFromDb(user));
							doctor(user);

						} else if (roleId == 4) {
							
							System.out.println("Welcome Phramasict "
									+ daoService.getUserNameFromDb(user));
							pharmasicst(user);
							break;
						} else if (roleId == 5) {
							System.out.println("Lab technician\n");
							System.out.println("Welcome "
									+ daoService.getUserNameFromDb(user));
							labTechnician(user);
							break;
						} else {
							System.out
									.println("\nYou Are Not Authorized With Any Department,Contact Admin for more details\n");
							unAuthorized(user);

						}
					} else {
						DateTimeFormatter dtf = DateTimeFormatter
								.ofPattern("dd/MM/yyyy HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						FileWriter fw = new FileWriter("loginHistory.txt", true);
						fw.write("username : " + user.getUserName()
								+ "password : " + user.getPassWord() + " "
								+ dtf.format(now) + "\n");
						fw.close();
					}
				} catch (SQLException e) {
					System.out.println(e);
				}
			}
			if (i >= 3) {

				System.out
						.println("\t\t\t3 Login attempts failed,please try after some time.Check with admin for login history");
				Thread.sleep(10000);
			}
		}

	}

	private static void labTechnician(User user) throws SQLException {
		System.out.println("\n\t\t\t\t\t1.Test Management"+
	"\n\t\t\t\t\t2.Doctor Prescription List\n\t\t\t\t\t3.Search Test\n\t\t\t\t\t5.Disable Test");
		char choice = 'n';
		while (true) {
			System.out.println("\n\nDo you want to logout(Y/N):");
			choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				System.out.println("\nThanks for your service "+daoService.getUserNameFromDb(user));
				System.exit(0);
				break;
			}
		}
		
	}

	private static void pharmasicst(User user) throws SQLException {
		System.out.println("\n\t\t\t\t\t1.Medicine Management"+
	"\n\t\t\t\t\t2.Doctor Prescription List\n\t\t\t\t\t3.Search Medicine\n\t\t\t\t\t5.Disable Medicine");
		char choice = 'n';
		while (true) {
			System.out.println("\n\nDo you want to logout(Y/N):");
			choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				System.out.println("\nThanks for your service "+daoService.getUserNameFromDb(user));
				System.exit(0);
				break;
			}
		}
		
	}

	private static void receptionist(User user) throws SQLException {
		System.out.println("\n\t\t\t\t\t1.Patient Management"+
	"\n\t\t\t\t\t2.Patient List\n\t\t\t\t\t3.Search Patient\n\t\t\t\t\t5.Disable Patient");
		char choice = 'n';
		while (true) {
			System.out.println("\n\nDo you want to logout(Y/N):");
			choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				System.out.println("\nThanks for your service "+daoService.getUserNameFromDb(user));
				System.exit(0);
				break;
			}
		}
		
		
	}

		
	

	private static void Admin(User user) throws SQLException {
		System.out.println("\n\t\t\t\t\t1.Staff Management"+
	"\n\t\t\t\t\t2.Staff List\n\t\t\t\t\t3.Search Staff\n\t\t\t\t\t5.Disable Staff");
		char choice = 'n';
		while (true) {
			System.out.println("\n\nDo you want to logout(Y/N):");
			choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				System.out.println("\nThanks for your service "+daoService.getUserNameFromDb(user));
				System.exit(0);
				break;
			}
		}
		
		
	}

	private static void unAuthorized(User user) throws SQLException {
		char choice = 'n';
		while (true) {
			System.out.println("\nDo you want to logout(Y/N):");
			choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				System.out.println("Thanks for your service "+daoService.getUserNameFromDb(user));
				System.exit(0);
				break;
			}
		}

	}

	private static void doctor(User user) throws Exception {

		List<Appoinments> listappoinment = daoService.appoinmentTable(user);
		try {
			if (listappoinment.get(0).getPatientName() == null) {

			} else {
				DateTimeFormatter dtf = DateTimeFormatter
						.ofPattern("dd/MM/yyyy");
				LocalDateTime now = LocalDateTime.now();
				System.out.println("\n\t\t\t\t\tTodays Appointments ("
						+ dtf.format(now) + ")");
				System.out
						.println("\t\t\t\t\t********************************");
				System.out.format("\t\t%-20s%-20s%-20s%-20s%-25s%n",
						"Token No", "Patient Reg.NO", "Name", "Gender",
						"Mobile");
				System.out
						.println("\t\t==========================================================================================");
				for (Appoinments a : listappoinment) {
					System.out.println("\t\t" + a);
					System.out
							.println("\t\t-------------------------------------------------------------------------------------------");
				}

			}
		} catch (Exception e) {
			System.out.println("You don't have Appoinment today");
			while (true) {
				System.out.println("\nDo you want to logout(Y/N):");
				char choice = input.nextLine().charAt(0);
				if (choice == 'Y' || choice == 'y') {
					System.out.println("\nThanks you for your service "+"Dr."+daoService.getUserNameFromDb(user));
					System.exit(0);
				} else if (choice == 'n' || choice == 'N') {
					doctor(user);
				}
			}
		}
		while (true) {
			try {
				System.out
						.println("\n1.Select Patient From Appointments List\n2.Logout");
				int choice = Integer.parseInt(input.next());

				if (choice == 1) {
					patient(user);
					break;
				} else if (choice == 2) {
					System.out.println("\nThanks you for your service Dr."+daoService.getUserNameFromDb(user));
					System.exit(0);
					
				} else {
					System.out.println("!!!Invalid option!!!");
				}
			} catch (Exception e) {
				System.out.println("!!!Invalid option!!");
			}
		}

	}

	private static void patient(User user) throws Exception {

		while (true) {
			try {
				System.out.println("\nSelect token:");
				int token = Integer.parseInt(input.nextLine());
			
				PatientData paitent = daoService.patientData(token,user);
				System.out.println(paitent);
				if (paitent != null) {
					System.out.println("\n\t\t\t\tToken no : " + token
							+ " Patient details");
					System.out
							.println("\t\t\t\t********************************");
					System.out.format("\t\t%-20s%-20s%-20s%-20s%n",
							"Patient Reg.No", "Patient Name", "Gender",
							"Blood Gruop");
					System.out
							.println("\t\t=======================================================================");
					System.out.println("\t\t" + paitent);

					while (true) {
						System.out
								.println("\nDo you want to see patient history(Y/N)?");
						char choice = input.nextLine().charAt(0);
						if (choice == 'y' || choice == 'Y') {
							int appoinmentCount = daoService
									.getAppinmentCount(paitent);
							if (appoinmentCount >= 2) {
								List<NewDiagnosis> patientHistoryDiagnos = daoService
										.patientHistoryDignos(paitent);
								boolean m=true;
								if(patientHistoryDiagnos.isEmpty()){
									m=false;
								}
								if(m){
								System.out
										.println("\n\t\t\t\t\tDiagnosis History");
								System.out
										.println("\t\t\t\t\t*******************");
								System.out.format("\t\t%-30s%-25s%-25s%-20s%n",
										"Diagnos","Doctor Name","Specialization", "Date");
								System.out
										.println("\t\t==========================================================================================");
								
								for (NewDiagnosis a : patientHistoryDiagnos) {
									System.out.println("\t\t"
											+ a.displayDiagnos());
									System.out
											.println("\t\t-------------------------------------------------------------------------------------------");

								}
								}
								List<NewDiagnosis> patientHistoryMedicine = daoService
										.patientHistoryMedicine(paitent);
								boolean x = true;
								if (patientHistoryMedicine.isEmpty()) {
									x = false;
								}
								if (x) {
									System.out
											.println("\n\t\t\t\t\tMedicine Prescription History");
									System.out
											.println("\t\t\t\t\t*****************************");
									System.out.format(
											"\t\t%-20s%-10s%-10s%-20s%-20s%-20s%n",
											"Medicine Name", "Dosage", "Days","Doctor Name","Specialization",
											"Date");
									System.out
											.println("\t\t============================================================================================");
									for (NewDiagnosis a : patientHistoryMedicine) {
										System.out.println("\t\t"
												+ a.displayMedicine());
										System.out
												.println("\t\t------------------------------------------------------------------------------------------");
									}

								}
								List<NewDiagnosis> patientHistoryTest = daoService
										.patientHistoryTest(paitent);
								boolean y = true;
								if (patientHistoryTest.isEmpty()) {
									y = false;
								}
								if (y) {
									System.out
											.println("\n\t\t\t\t\tTest Prescription History");
									System.out
											.println("\t\t\t\t\t*************************");
									System.out.format("\t\t%-30s%-20s%-20s%-20s%n",
											"Test Name","Doctor Name","Specialization", "Date");
									System.out
											.println("\t\t===============================================================================");

									for (NewDiagnosis a : patientHistoryTest) {
										System.out.println("\t\t"
												+ a.displayTest());
										System.out.println("\t\t------------------------------------------------------------------------------");
									}                             
									report(paitent);
								}
								List<NewDiagnosis> patientHistoryNotes = daoService
										.patientHistoryNotes(paitent);
								boolean n=true;
								if(patientHistoryNotes.isEmpty()){
									n=false;
								}
								if(n){
								System.out
										.println("\n\t\t\t\t\tDoctor Notes History");
								System.out
										.println("\t\t\t\t\t*********************");
								System.out.format("\t\t%-30s%-20s%-20s%-20s%n",
										"Notes","Doctor Name","Specialization", "Date");
								System.out
										.println("\t\t===================================================================================");
								
								for (NewDiagnosis a : patientHistoryNotes) {
									System.out.println("\t\t"
											+ a.displayNotes());
									System.out.println("\t\t---------------------------------------------------------------------------------");
									                    
								}
								}
								if(m){
									if(n){
										if(x){
											if(y){
												
											}
										}
									}
								}else{
									System.out
									.println("\nNew patient,no history found");
								}
			
							} else {
								System.out
										.println("\nNew patient,no history found");
							}
							newDiagnos(paitent, token, user);
							break;
						} else if (choice == 'n' || choice == 'N') {
							newDiagnos(paitent, token, user);
							break;
						} else {
							System.out.println("!!!Invalid option!!!");
						}

					}
				} else {
					System.out.println("Check token number");
				}
				}

			catch (Exception e) {
				System.out.println("Check token number");
			}
		}
	}

	private static void report(PatientData paitent) throws SQLException,
			Exception {
		boolean k=true;
		List<NewDiagnosis> report = new ArrayList<NewDiagnosis>();
		report = daoService.displayReport(paitent);
		if(report.isEmpty()){
			k=false;
		}
		if(k){
		System.out.println("\n\t\t\t\t\tTest Reports");
		System.out.println("\t\t\t\t\t************");
		System.out.format("\n\t%-25s%-35s%-35s%-35s%-20s%-20s%-20s%n",
				"Test Name", "Low Range", "High Range", "Actual Value", "Doctor Name","Specialization","Date");
		System.out
				.println("\t=====================================================================================================================================================================================");
		for (NewDiagnosis a : report) {
			System.out.println("\t" + a.displayReport());
			System.out
					.println("\t---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		}
		}

	}

	private static void newDiagnos(PatientData paitent, int token, User user)
			throws Exception {
		List<NewDiagnosis> diagnosMed = new ArrayList<NewDiagnosis>();
		List<NewDiagnosis> diagnosTest = new ArrayList<NewDiagnosis>();
		newDiagnosis = new NewDiagnosis();
		// System.out.println("Add diagnosis:");
		// newDiagnosis.setDiagnos(input.nextLine());

		System.out.println("Add Diagnose:");
		String dia = input.nextLine();
		newDiagnosis.setDiagnos(dia);

		char medChoice = 'n';
		do {

			newDiagnosis = new NewDiagnosis();
			System.out.println("\n\nSelect medicine option from following:");
			System.out
					.println("\n1.Paracetamol\n2.Lisinopril\n3.Metformin\n4.Atorvastatin"

							+ "\n5.Amlodipine\n6.Candesartan\n7.Sertraline\n8.Candesartan\n9.Perindopril\n10.No medicine now");
			
			int b=newDiagnosis.setMedicineId(input.nextLine());
			if(b==10){
				break;
			}
			System.out.println("\nSelect dosage from following:");
			System.out
					.println("1.1-0-0\n2.0-1-0\n3.0-0-1\n4.1-1-0\n5.1-0-1\n6.0-1-1\n7.1-1-1");
			newDiagnosis.setDoasageId(input.nextLine());
			System.out.println("\nEnter days for medicine course:");
			newDiagnosis.setDays(input.nextLine());
		
				diagnosMed.add(newDiagnosis);

	
			
			System.out.println("\nDo you want to add more medicine(Y/N):");
			medChoice = input.nextLine().charAt(0);

		} while (medChoice == 'Y' || medChoice == 'y');
		char testChoice = 'n';
		do {
			newDiagnosis = new NewDiagnosis();
			System.out.println("\nSelect Test from following:");
			System.out
					.println("\n1.Platelet count\n2.Hemoglobin A1C\n3.Glucose test"
							+ "\n4.Kidney function test\n5.Amylase test\n6.Liver function tests"
							+ "\n7.Lipid profile\n8.Vitamin D test\n9.BP test\n10.No test now");
			int c=newDiagnosis.setTestId(input.nextLine());
			if (c==10) {
				break;
			}

			diagnosTest.add(newDiagnosis);
			System.out.println("\nDo you want to add more test(Y/N):");
			testChoice = input.nextLine().charAt(0);

		} while (testChoice == 'Y' || testChoice == 'y');
		System.out.println("\nAdd Notes:");
		newDiagnosis.setNotes(input.nextLine());
		newDiagnosis.setDiagnos(dia);
		// boolean consultation = false;
		if (daoService.insertDiagnos(newDiagnosis, paitent)) {
			if (daoService.insertMedicine(diagnosMed, paitent)) {
			}
			if (daoService.insertTest(diagnosTest, paitent)) {
				// consultation = true;

			}
		}

		if (daoService.disableToken(token)) {
			System.out.println("\n\nConsulation Completed...");
		}
		while (true) {
			System.out
					.println("\nDo you want to continue with next patient(Y/N)?");
			char choice = input.nextLine().charAt(0);
			if (choice == 'Y' || choice == 'y') {
				doctor(user);
			} else if (choice == 'N' || choice == 'n') {
				System.out.println("\nThanks you for your service Dr."+daoService.getUserNameFromDb(user));
				System.exit(0);
			} else {
				System.out.println("Invalid input");
			}
		}

	}

}
