package interfaces;

import java.util.List;

import dto.UserDTO;
import exceptions.DALException;


public interface IUserDAO {
	/**
	 * 
	 * @param userID
	 * @return user with specified userID
	 * @throws DALException
	 */
	UserDTO getUser(int userID) throws DALException;
	/**
	 * 
	 * @return list of all users
	 * @throws DALException
	 */
	List<UserDTO> getUserList() throws DALException;
	/**
	 * 
	 * @param user
	 * @return 
	 * @throws DALException
	 */
	void createUser(UserDTO user) throws DALException;
	/**
	 * 
	 * @param user
	 * @throws DALException
	 */
	void updateUser(UserDTO user) throws DALException;
}
