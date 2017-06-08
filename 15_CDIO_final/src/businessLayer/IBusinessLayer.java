package businessLayer;

import java.util.List;
import dto.UserDTO;

import businessLayer.BusinessLayerImplementation;
import exceptions.DALException;

public interface IBusinessLayer {
	UserDTO getUser(int userID) throws DALException; 
	List<UserDTO> getUserList() throws DALException; 
	void createUser(UserDTO user) throws DALException; 
	void updateUser(UserDTO user) throws DALException;  
//	String createPassword(UserDTO pwg) throws DALException;
//	void checkPassword(String password) throws DALException;
//	boolean checkCpr(String cpr) throws DALException;
//	int productBatchIDGenerator() throws DALException;
//	void checkID(int ID) throws DALException;
	

	
}
