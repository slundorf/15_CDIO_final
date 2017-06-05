package dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import dto.RoleDTO;
import exceptions.DALException;
import interfaces.IRoleDAO;

public class SerRoleDAO implements IRoleDAO {
	private List<RoleDTO> roles = new ArrayList<RoleDTO>();
	private final String pathName;
	
	public SerRoleDAO(){
	pathName="RoleDB.ser";	
	}
	/**
	 * 
	 * @param roleID
	 * @return Role with specified roleID
	 * @throws DALException
	 */
	@Override
	public RoleDTO getRoles(int roleID) throws DALException {
		loadInfo();
		if(roles.size() == 0)
			throw new DALException("The database is empty.");
		for(int i = 0; i < roles.size(); i++) {
			if (roles.get(i).getRoleID() == roleID) {
				return roles.get(i);
			}
		}
		throw new DALException("No roles have been found with id: " + roleID);
	}
	/**
	 * 
	 * @return A list of all roles
	 * @throws DALException
	 */
	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		loadInfo();
		if(roles.size() == 0)
			throw new DALException("There are no roles in this database");
		return roles;
	}
	/**
	 * Creates the role given as parameter.
	 * @param role
	 * @throws DALException
	 */
	@Override
	public void createRole(RoleDTO Role) throws DALException {
		loadInfo();
		roles.add(Role);
		saveInfo();
	}
	/**
	 * Updates the role, given as parameter.
	 * @param role
	 * @throws DALException
	 */
	@Override
	public void updateRole(RoleDTO Role) throws DALException {
		loadInfo();
		for(int i = 0; i< roles.size(); i++) {
			if(Role.getRoleID() == roles.get(i).getRoleID()) {
				roles.remove(i);
				roles.add(Role);
			}
		}
	}

	/**
	 * Loads the role arraylist
	 */
	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			roles = (ArrayList<RoleDTO>) input.readObject();
			if (roles.equals(null))
				roles = new ArrayList<RoleDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			roles = new ArrayList<RoleDTO>();
		} catch (StreamCorruptedException e) {
			System.out.println("The file is currupted.");
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * saves the role arraylist to the .ser file.
	 */
	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(roles);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
