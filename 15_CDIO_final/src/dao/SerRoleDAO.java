package dao;

import java.util.List;
import dto.RoleDTO;
import exceptions.DALException;
import interfaces.IRoleDAO;

public class SerRoleDAO extends SerDAO<RoleDTO> implements IRoleDAO {
	
	public SerRoleDAO(){
	super("RoleDB.ser");	
	}
	@Override
	public RoleDTO getRoles(int roleID) throws DALException {
		loadInfo();
		if(list.size() == 0)
			throw new DALException("The database is empty.");
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
		if(list.size() == 0)
			throw new DALException("There are no roles in this database");
		return list;
	}

	@Override
	public void createRole(RoleDTO Role) throws DALException {
		loadInfo();
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
