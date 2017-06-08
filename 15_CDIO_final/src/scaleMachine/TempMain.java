package scaleMachine;

import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import exceptions.scaleConnectionException;
import interfaces.IUserDAO;
import serDAO.SerUserDAO;

public class TempMain {

	public static void main(String[] args) throws scaleConnectionException{
		IUserDAO userDAO = new SerUserDAO();
		ScaleConnection connection = new ScaleConnection("169.254.2.3");
//		UserDTO user = new UserDTO(85, "Kurt", "kru", "010202-1234", " lkjdwsjgoiwkm3r", new RoleDTO(3,"Operator"), true);
//		userDAO.createUser(user);
		
		
//		user.setIni("HOO");
//		
//		
		UserDTO user;
		user = null;
		boolean attempt = true;
		while (user == null) {
			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");

			// validate user;
			try {
				user = userDAO.getUser(userId);
			} catch (DALException e) {
				//Try again
			}
			attempt = false;
		}
		connection.setOperatorInitials(user.getIni());
//		
		
	}
}
