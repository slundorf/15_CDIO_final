package businessLayer;

import java.util.List;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchDTO;
import dto.UserDTO;

import businessLayer.BusinessLayerImplementation;
import exceptions.DALException;

public interface IBusinessLayer {
	UserDTO getUser(int userID) throws DALException; 
	List<UserDTO> getUserList() throws DALException; 
	void createUser(UserDTO user) throws DALException; 
	void updateUser(UserDTO user) throws DALException;
	void createIngredient(IngredientDTO ing) throws DALException;
	void createIngredientBatch(IngredientBatchDTO iB) throws DALException;
	void createProductBatch(ProductBatchDTO pb) throws DALException;  
	String createPassword() throws DALException;
	void checkPassword(String password) throws DALException;
	void checkUpdatedUser(UserDTO user) throws DALException;
}
