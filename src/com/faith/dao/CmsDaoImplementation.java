package com.faith.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.faith.bean.Appoinments;
import com.faith.bean.NewDiagnosis;
import com.faith.bean.PatientData;
import com.faith.bean.User;
import com.faith.config.ConnectionFactory;

public class CmsDaoImplementation implements ICmsDaoService {
	// query
	static String SEARCH_USER = "SELECT * FROM USERS WHERE userName=? and passWord=?";
	static String DISPLAY_APP = "SELECT * FROM APPOINMENTS INNER JOIN PATIENTS ON APPOINMENTS.PATIENTID=PATIENTS.PATIENTID INNER JOIN STAFFS ON STAFFS.STAFFID=APPOINMENTS.STAFFID WHERE userId=? AND APPOINMENTS.isActive='yes' AND DATE(APPOINMENTS.createdOn) = CURDATE()";
	static String PATIENT_DATA = "SELECT * FROM APPOINMENTS INNER JOIN PATIENTS ON APPOINMENTS.PATIENTID=PATIENTS.PATIENTID inner join staffs on STAFFS.STAFFID=APPOINMENTS.STAFFID WHERE TOKENNO=? AND userId=? ";
	static String INSERT_DIAGNOS = "INSERT INTO prescriptions(diagnos,notes,appoinmentId)values(?,?,?)";
	static String INSERT_MEDICINE = "INSERT INTO medicine_prescription(medicinesId,dosageId,days,appoinmentId)values(?,?,?,?)";
	static String INSERT_TEST = "INSERT INTO test_prescription(testId,appoinmentId)values(?,?)";
	static String APPOINMENT_COUNT="SELECT count(patientId) from appoinments where patientId=?";
	static String DISABLE_APPOINMENT="UPDATE APPOINMENTS SET ISACTIVE='false' WHERE TOKENNO=? ";
	// connection
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private CallableStatement callableStatement = null;

	public boolean checkLogin(User user) throws Exception {
		try {
			// open connection
			connection = ConnectionFactory.getDataBaseConnection();
			statement = connection.prepareStatement(SEARCH_USER);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				String dbPassWord = resultSet.getString("passWord");
				if (user.getPassWord().equals(dbPassWord)) {
					return true;
				}

			} else {
				System.out.println("invalid login details......");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			statement.close();
			resultSet.close();
		}
		return false;
	}

	public int getUserIdFromDb(User user) throws Exception {
		int userIdFromDb = 0;
		// open connection
		try {
			connection = ConnectionFactory.getDataBaseConnection();
			statement = connection.prepareStatement(SEARCH_USER);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userIdFromDb = resultSet.getInt("roleId");
				return userIdFromDb;
			} else {
				System.out.println("not found..");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			statement.close();
			resultSet.close();
		}
		return userIdFromDb;
	}

	public String getUserNameFromDb(User user) throws SQLException {
		String userNameFromDb = "";
		// open connection
		try {
			connection = ConnectionFactory.getDataBaseConnection();
			statement = connection.prepareStatement(SEARCH_USER);
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getPassWord());
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				userNameFromDb = resultSet.getString("userName");
				return userNameFromDb;
			} else {
				System.out.println("not found..");
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			statement.close();
			resultSet.close();
		}
		return userNameFromDb;
	}

	public List<Appoinments> appoinmentTable(User user) throws Exception {
		 
		 List<Appoinments> list = new ArrayList();

		connection = ConnectionFactory.getDataBaseConnection();
		int a = getUserFromStaffs(user);
		user.setUserId(a);

		statement = connection.prepareStatement(DISPLAY_APP);
		statement.setInt(1, a);
		
		
		resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int appoinmentId = resultSet.getInt("appoinmentId");
			int staffId = resultSet.getInt("staffId");
			int patientId = resultSet.getInt("patientId");
			int tokenNo = resultSet.getInt("tokenNo");
			String patientRegNo = resultSet.getString("patientRegNo");
			String patientName = resultSet.getString("patientName");
			String patientGender = resultSet.getString("patientGender");
			String patientBloodGroup = resultSet.getString("patientBloodGroup");
			String patientMobile = resultSet.getString("patientMobile");
			list.add(new Appoinments(appoinmentId, staffId, patientId, tokenNo,
					patientRegNo, patientName, patientGender,
					patientBloodGroup, patientMobile));

		}

		return list;
	}



	public int getUserFromStaffs(User user) throws Exception {
		int userIdFromStaff = 0;
		// open connection
		connection = ConnectionFactory.getDataBaseConnection();
		statement = connection.prepareStatement(SEARCH_USER);
		statement.setString(1, user.getUserName());
		statement.setString(2, user.getPassWord());
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			userIdFromStaff = resultSet.getInt("userId");
			return userIdFromStaff;
		} else {
			System.out.println("not found..");
		}

		return userIdFromStaff;
	}

	public List<NewDiagnosis> patientHistory(PatientData appList)
			throws Exception {

		List<NewDiagnosis> patientHistoy = new ArrayList();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = appList.getPatientId();
		callableStatement = connection.prepareCall("{call patientHistory(?)}");
		callableStatement.setInt(1, a);
		resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			String diagnos = resultSet.getString("diagnos");
			String notes = resultSet.getString("notes");
			String medicineName = resultSet.getString("medicineName");
			String dosage = resultSet.getString("dosage");
			int days = resultSet.getInt("days");
			String testName = resultSet.getString("testName");
			Date createdOn = resultSet.getDate("createdOn");
			patientHistoy.add(new NewDiagnosis(diagnos, notes, createdOn,
					testName, medicineName, dosage, days));

		}

		return patientHistoy;
	}

	public PatientData patientData(int token,User user) throws Exception {
		PatientData data = null;
		System.out.println(user);
		connection = ConnectionFactory.getDataBaseConnection();
		statement = connection.prepareStatement(PATIENT_DATA);
		statement.setInt(1, token);
		statement.setInt(2, user.getUserId());
		
        
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int appoinmentId = resultSet.getInt("appoinmentId");
			int staffId = resultSet.getInt("staffId");
			int patientId = resultSet.getInt("patientId");
			int tokenNo = resultSet.getInt("tokenNo");
			String patientRegNo = resultSet.getString("patientRegNo");
			String patientName = resultSet.getString("patientName");
			String patientGender = resultSet.getString("patientGender");
			String patientBloodGroup = resultSet.getString("patientBloodGroup");
			data = new PatientData(appoinmentId, staffId, patientId, tokenNo,
					patientRegNo, patientName, patientGender, patientBloodGroup);

		}
		

		return data;

	}


	public boolean insertDiagnos(NewDiagnosis newDiagnosis,
			PatientData patientData) throws SQLException {
		boolean result = false;
		try {
			// open connection
			connection = ConnectionFactory.getDataBaseConnection();
			// create statement
			statement = connection.prepareStatement(INSERT_DIAGNOS);
			statement.setString(1, newDiagnosis.getDiagnos());
			statement.setString(2, newDiagnosis.getNotes());
			statement.setInt(3, patientData.getAppoinmentId());
			if (1 == statement.executeUpdate()) {
				result = true;
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			statement.close();
		}

		return result;
	}

	public boolean insertMedicine(List<NewDiagnosis> diagnosMed,
			PatientData patientData) throws SQLException {
		boolean result = false;
		try {
			// open connection
			connection = ConnectionFactory.getDataBaseConnection();
			// create statement
			statement = connection.prepareStatement(INSERT_MEDICINE);
			for (int i = 0; i < diagnosMed.size(); i++) {
				statement.setInt(1, diagnosMed.get(i).getMedicineId());
				statement.setInt(2, diagnosMed.get(i).getDoasageId());
				statement.setInt(3, diagnosMed.get(i).getDays());
				statement.setInt(4, patientData.getAppoinmentId());
				if (1 == statement.executeUpdate()) {
					result = true;
				}
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			statement.close();
		}

		return result;
	}

	public boolean insertTest(List<NewDiagnosis> diagnosTest,
			PatientData paitent) throws SQLException {
		boolean result = false;
		try {
			// open connection
			connection = ConnectionFactory.getDataBaseConnection();
			// create statement
			statement = connection.prepareStatement(INSERT_TEST);
			for (int i = 0; i < diagnosTest.size(); i++) {
				statement.setInt(1, diagnosTest.get(i).getTestId());
				statement.setInt(2, paitent.getAppoinmentId());
				if (1 == statement.executeUpdate()) {
					result = true;
				}
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			statement.close();
		}

		return result;
	}

	public List<NewDiagnosis> patientHistoryDignos(PatientData appList) throws Exception {

		List<NewDiagnosis> patientHistoyDiagnos = new ArrayList();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = appList.getPatientId();
		callableStatement = connection.prepareCall("{call patientHistoryDiagnos(?)}");
		callableStatement.setInt(1, a);
		resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			String diagnos = resultSet.getString("diagnos");
			Date createdOn = resultSet.getDate("createdOn");
			String staffName=resultSet.getString("staffName");
			String specialization=resultSet.getString("specialization");
			
			patientHistoyDiagnos.add(new NewDiagnosis(diagnos, createdOn,staffName,specialization));

		}

		return patientHistoyDiagnos;
	}

	public List<NewDiagnosis> patientHistoryMedicine(PatientData appList) throws Exception {

		List<NewDiagnosis> patientHistoyMedicine = new ArrayList();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = appList.getPatientId();
		callableStatement = connection.prepareCall("{call patientHistoryMedicine(?)}");
		callableStatement.setInt(1, a);
		resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			String medicineName = resultSet.getString("medicineName");
			String dosage = resultSet.getString("dosage");
			int days = resultSet.getInt("days");
			Date createdOn = resultSet.getDate("createdOn");
			String staffName=resultSet.getString("staffName");
			String specialization=resultSet.getString("specialization");
			
			patientHistoyMedicine.add(new NewDiagnosis(createdOn,medicineName,staffName,specialization, dosage, days));

		}

		return patientHistoyMedicine;
	}

	public List<NewDiagnosis> patientHistoryTest(PatientData appList) throws Exception {

		List<NewDiagnosis> patientHistoyMedicine = new ArrayList();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = appList.getPatientId();
		callableStatement = connection.prepareCall("{call patientHistoryTest(?)}");
		callableStatement.setInt(1, a);
		resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			String testName = resultSet.getString("testName");
			Date createdOn = resultSet.getDate("createdOn");
			String staffName=resultSet.getString("staffName");
			String specialization=resultSet.getString("specialization");
			
			patientHistoyMedicine.add(new NewDiagnosis(createdOn,testName,staffName,specialization));

		}

		return patientHistoyMedicine;
	}

	public List<NewDiagnosis> patientHistoryNotes(PatientData paitent) throws Exception {
		List<NewDiagnosis> patientHistoyNotes = new ArrayList();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = paitent.getPatientId();
		callableStatement = connection.prepareCall("{call patientHistoryDiagnos(?)}");
		callableStatement.setInt(1, a);
		resultSet = callableStatement.executeQuery();
		while (resultSet.next()) {
			String notes = resultSet.getString("notes");
			Date createdOn = resultSet.getDate("createdOn");
			String staffName=resultSet.getString("staffName");
			String specialization=resultSet.getString("specialization");
			patientHistoyNotes.add(new NewDiagnosis(staffName,specialization, notes,createdOn));

		}

		return patientHistoyNotes;
	}

	public int getAppinmentCount(PatientData paitent) throws Exception {
		int appoinmentCount=0;
		connection = ConnectionFactory.getDataBaseConnection();
		statement = connection.prepareStatement(APPOINMENT_COUNT);
		statement.setInt(1,paitent.getPatientId() );
		resultSet=statement.executeQuery();
		resultSet.next();
		appoinmentCount=resultSet.getInt(1);
		
		
		return appoinmentCount;
	}

	public List<Appoinments> getTokenList() throws Exception {
		List<Appoinments> tokenList=new ArrayList<Appoinments>();
		connection = ConnectionFactory.getDataBaseConnection();
		statement = connection.prepareStatement(DISPLAY_APP);

		resultSet = statement.executeQuery();
		if(resultSet.next()){
			int token=resultSet.getInt("tokenNo");
			tokenList.add(new Appoinments(token) );
		}
		return tokenList;
	}

	public boolean disableToken(int token) throws SQLException {	
		boolean result = false;
		try {
			// open connection
			connection = ConnectionFactory.getDataBaseConnection();
			// create statement
			statement = connection.prepareStatement(DISABLE_APPOINMENT);
			statement.setInt(1, token);
			if (1 == statement.executeUpdate()) {
				result = true;
			}

		} catch (Exception err) {
			System.out.println(err);
		} finally {
			statement.close();
		}

		return result;
	}

	public List<NewDiagnosis> displayReport(PatientData paitent) throws Exception {
		List<NewDiagnosis> displayReport=new ArrayList<NewDiagnosis>();
		connection = ConnectionFactory.getDataBaseConnection();
		int a = paitent.getPatientId();
		callableStatement = connection.prepareCall("{call displayReport(?)}");
		callableStatement.setInt(1, paitent.getPatientId());
		resultSet = callableStatement.executeQuery();
		while(resultSet.next()) {
			String testName=resultSet.getString("testName");
			String lowRange=resultSet.getString("lowRange");
			String highRange=resultSet.getString("highRange");
			String actualValue=resultSet.getString("actualValue");
			Date createdOn=resultSet.getDate("createdOn");
			//
			String staffName=resultSet.getString("staffName");
			String specialization=resultSet.getString("specialization");
			displayReport.add(new NewDiagnosis(createdOn,testName,staffName,specialization,lowRange,highRange,actualValue));


		}

		return displayReport;
	}

}
