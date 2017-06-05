package interfaces;

import java.util.List;

import dto.RoleDTO;
import exceptions.DALException;

public interface IRoleDAO {
	RoleDTO getRoles(int roleID) throws DALException;
	List<RoleDTO> getRoleList() throws DALException;
	void createRole(RoleDTO Role) throws DALException;
	void updateRole(RoleDTO Role) throws DALException;
}
