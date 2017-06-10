package serDAO;

import java.util.List;
import dto.RoleDTO;
import exceptions.DALException;
import interfaces.IRoleDAO;

public class SerRoleDAO extends SerDAO<RoleDTO> implements IRoleDAO {
	public SerRoleDAO(String pathName){
		super(pathName);
	}
	
	public SerRoleDAO(){
	super("Ser/RoleDB.ser");
	}
	@Override
	public RoleDTO getRole(int roleID) throws DALException {
		loadInfo();
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getRoleID() == roleID) {
				return list.get(i);
			}
		}
		throw new DALException("No roles have been found with id: " + roleID);
	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		loadInfo();
		return list;
	}

	@Override
	public void createRole(RoleDTO Role) throws DALException {
		loadInfo();
		for(int i=0;i<list.size();i++)
			if(list.get(i).getRoleID()==Role.getRoleID())
				throw new DALException("A role with ID "+Role.getRoleID()+" already exists");
		list.add(Role);
		saveInfo();
	}
	@Override
	public void updateRole(RoleDTO Role) throws DALException {
		loadInfo();
		for(int i = 0; i< list.size(); i++) {
			if(Role.getRoleID() == list.get(i).getRoleID()) {
				list.remove(i);
				list.add(Role);
			}
		}
	}
}
