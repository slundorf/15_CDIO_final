package interfaces;

import java.util.List;

import dto.RoleDTO;
import exceptions.DALException;

public interface IRoleDAO {
	/**
	 * 
	 * @param roleID
	 * @return role
	 * @throws DALException
	 */
	RoleDTO getRoles(int roleID) throws DALException;
	/**
	 * 
	 * @return list of roles
	 * @throws DALException
	 */
	List<RoleDTO> getRoleList() throws DALException;
	/**
	 * 
	 * @param role
	 * @throws DALException
	 */
	void createRole(RoleDTO role) throws DALException;
	/**
	 * 
	 * @param role
	 * @throws DALException
	 */
	void updateRole(RoleDTO role) throws DALException;
}
