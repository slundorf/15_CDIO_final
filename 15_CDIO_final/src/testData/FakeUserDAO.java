///**
// * 
// */
//package testData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dto.UserDTO;
//import dto.RoleDTO;
//import exceptions.DALException;
//import interfaces.IUserDAO;
//
//
///**
// * @author s153932
// *
// */
//public class FakeUserDAO implements IUserDAO {
//	List<UserDTO> fakeUserList;
//	UserDTO user1;
//	UserDTO user2;
//	UserDTO user3;
//	/* (non-Javadoc)
//	 * @see interfaces.IUserDAO#getUser(int)
//	 */
//	public FakeUserDAO() {
//		fakeUserList = new ArrayList<UserDTO>();
//		user1 = new UserDTO(11, "Steve", "STV", "101010-1234","wally", new RoleDTO(4,"Operator"), true);
//		user2 = new UserDTO(12, "Kurt", "Kru", "101110-1234","wally2", new RoleDTO(1,"Administator"), true);
//		user3 = new UserDTO(13, "Lis", "LIS", "101110-1234","wally3", new RoleDTO(2,"Pharmacist"), false);
//		fakeUserList.add(user1);
//		fakeUserList.add(user2);
//		fakeUserList.add(user3);
//		
//	}
//	
//	@Override
//	public UserDTO getUser(int userID) throws DALException {
//		/* switch case der returnerer userDTO'er 1/2stk
//		 * ellers returnerer den exception.
//		 */
//		 
//		for (int i = 0; i < fakeUserList.size(); i++) {
//			if (fakeUserList.get(i).getUserID() == userID) {
//				return fakeUserList.get(i);
//			}
//		}
//		throw new DALException("No user has been found with id: " + userID);
//	}
//
//	/* (non-Javadoc)
//	 * @see interfaces.IUserDAO#getUserList()
//	 */
//	@Override
//	public List<UserDTO> getUserList() throws DALException {
//		return fakeUserList;
//	}
//
//	/* (non-Javadoc)
//	 * @see interfaces.IUserDAO#createUser(dto.UserDTO)
//	 */
//	@Override
//	public void createUser(UserDTO user) throws DALException {
//		if (fakeUserList.size() == 88) {
//			throw new DALException("Database is full");
//		}
//		for(int i=0;i<fakeUserList.size();i++){
//			if(fakeUserList.get(i).getUserID()==user.getUserID()){
//				throw new DALException("A user with ID = " + user.getUserID() + " already exists.");
//			}
//		}
//		fakeUserList.add(user);
//		
//
//	}
//
//	/* (non-Javadoc)
//	 * @see interfaces.IUserDAO#updateUser(dto.UserDTO)
//	 */
//	@Override
//	public void updateUser(UserDTO user) throws DALException {
//		for (int i = 0; i < fakeUserList.size(); i++) {
//			if (user.getUserID() == fakeUserList.get(i).getUserID()) {
//				fakeUserList.remove(i);
//				fakeUserList.add(user);
//			}
//		}
//
//	}
//
//}
