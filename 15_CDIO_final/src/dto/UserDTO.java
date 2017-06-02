package dto;

import java.util.List;

public class UserDTO {
	private int userID;
	private String userName;
	private String ini;
	private String cpr;
	private String password;
	private RoleDTO role;
	
	
	/**
	 * Empty constructor
	 */
	public UserDTO(){}
	/**
	 * Constructor with parameters
	 * @param userID
	 * @param userName
	 * @param ini
	 * @param cpr
	 * @param password
	 * @param roles List of roles
	 */
	public UserDTO(int userID,String userName, String ini, String cpr, String password, List<RoleDTO> roles){
		this.setUserID(userID);
		this.setUserName(userName);
		this.setIni(ini);
		this.setCpr(cpr);
		this.setPassword(password);
		this.setRole(role);
	}
	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the ini
	 */
	public String getIni() {
		return ini;
	}
	/**
	 * @param ini the ini to set
	 */
	public void setIni(String ini) {
		this.ini = ini;
	}
	/**
	 * @return the cpr
	 */
	public String getCpr() {
		return cpr;
	}
	/**
	 * @param cpr the cpr to set
	 */
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the role of the user
	 */
	public RoleDTO getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleDTO role) {
		this.role = role;
	}
}