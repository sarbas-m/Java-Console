package com.faith.dao;

import java.sql.SQLException;
import java.util.List;

import com.faith.bean.Appoinments;
import com.faith.bean.NewDiagnosis;
import com.faith.bean.PatientData;
import com.faith.bean.User;

public interface ICmsDaoService {

	public boolean checkLogin(User user) throws Exception;

	// for inserting
	public boolean insertDiagnos(NewDiagnosis newDiagnosis,
			PatientData patientData) throws SQLException;

	public boolean insertMedicine(List<NewDiagnosis> diagnosMed,
			PatientData patientData) throws SQLException;

	public int getUserIdFromDb(User user) throws Exception;

	public String getUserNameFromDb(User user) throws SQLException;

	public List<Appoinments> appoinmentTable(User user) throws SQLException,
			Exception;

	public int getUserFromStaffs(User user) throws Exception;

	public List<NewDiagnosis> patientHistory(PatientData appList)
			throws SQLException, Exception;

	public PatientData patientData(int token, User user) throws Exception;

	public boolean insertTest(List<NewDiagnosis> diagnosTest,
			PatientData paitent) throws SQLException;
	
	public List<NewDiagnosis> patientHistoryDignos(PatientData appList) throws SQLException, Exception;
	public List<NewDiagnosis> patientHistoryMedicine(PatientData appList) throws Exception;
	public List<NewDiagnosis> patientHistoryTest(PatientData appList) throws Exception;

	public List<NewDiagnosis> patientHistoryNotes(PatientData paitent) throws SQLException, Exception;

	public int getAppinmentCount(PatientData paitent) throws Exception;

	public List<Appoinments> getTokenList() throws Exception;

	public boolean disableToken(int token) throws SQLException;

	public List<NewDiagnosis> displayReport(PatientData paitent) throws SQLException, Exception;

}
