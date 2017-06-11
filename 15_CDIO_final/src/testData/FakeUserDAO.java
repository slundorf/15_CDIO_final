/**
 * 
 */
package testData;

import java.util.ArrayList;
import java.util.List;

import dto.UserDTO;
import dto.RoleDTO;
import exceptions.DALException;
import interfaces.IUserDAO;


/**
 * @author s153932
 *
 */
public class FakeUserDAO implements IUserDAO {
	List<UserDTO> fakeUsers;
	UserDTO user1;
	UserDTO user2;
	/* (non-Javadoc)
	 * @see interfaces.IUserDAO#getUser(int)
	 */
	public FakeUserDAO() {
		fakeUsers = new ArrayList<UserDTO>();
		user1 = new UserDTO(11, "Steve", "STV", "101010-1234","wally", new RoleDTO(3,"Operator"), true);
		user2 = new UserDTO(12, "Kurt", "Kru", "101110-1234","wally", new RoleDTO(3,"Administrator"), true);
		fakeUsers.add(user1);
		fakeUsers.add(user2);
		
	}
	
	@Override
	public UserDTO getUser(int userID) throws DALException {
		/* switch case der returnerer userDTO'er 1/2stk
		 * ellers returnerer den exception.
		 */
		 
		if (fakeUsers.size() == 0)
			throw new DALException("The database is empty.");
		for (int i = 0; i < fakeUsers.size(); i++) {
			if (fakeUsers.get(i).getUserID() == userID) {
				return fakeUsers.get(i);
			}
		}
		throw new DALException("No user has been found with id: " + userID);
	}

	/* (non-Javadoc)
	 * @see interfaces.IUserDAO#getUserList()
	 */
	@Override
	public List<UserDTO> getUserList() throws DALException {
		return fakeUsers;
	}

	/* (non-Javadoc)
	 * @see interfaces.IUserDAO#createUser(dto.UserDTO)
	 */
	@Override
	public void createUser(UserDTO user) throws DALException {
		fakeUsers.add(user);
		

	}

	/* (non-Javadoc)
	 * @see interfaces.IUserDAO#updateUser(dto.UserDTO)
	 */
	@Override
	public void updateUser(UserDTO user) throws DALException {
		for (int i = 0; i < fakeUsers.size(); i++) {
			if (user.getUserID() == fakeUsers.get(i).getUserID()) {
				fakeUsers.remove(i);
				fakeUsers.add(user);
			}
		}

	}

}
