package testDataSer;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import dto.UserDTO;
import exceptions.DALException;
import interfaces.IUserDAO;

public class FakeSerUserDAO extends FakeSerDAO<UserDTO> implements IUserDAO {
	
	public FakeSerUserDAO(String pathName){
		super(pathName);
	}

	
	public FakeSerUserDAO(){
//		super("15_CDIO_final\\WebContent\\SerFiles\\UserDB.ser");
		super("UserDB.ser");
	}

	
	@Override
	public UserDTO getUser(int userID) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserID() == userID) {
				return list.get(i);
			}
		}
		throw new DALException("No user has been found with id: " + userID);
	}


	@Override
	public List<UserDTO> getUserList() throws DALException {
		loadInfo();
//		if (list.size() == 0)
//			throw new DALException("There are no users in the database.");
		return list;
	}
	

	@Override
	public void createUser(UserDTO user) throws DALException {

		loadInfo();
		if (list.size() == 88) {
			throw new DALException("Database is full");
		}
		for(int i=0;i<list.size();i++){
			if(list.get(i).getUserID()==user.getUserID()){
				throw new DALException("A user with ID = " + user.getUserID() + " already exists.");
			}
		}
		list.add(user);
		saveInfo();
	}

	
	@Override
	public void updateUser(UserDTO user) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (user.getUserID() == list.get(i).getUserID()) {
				list.remove(i);
				list.add(user);
			}
		}
		saveInfo();
	}
}