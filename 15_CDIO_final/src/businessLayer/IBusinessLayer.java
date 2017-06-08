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
	
}
