package dto;

public class RoleDTO {
	private int roleID;
	private String roleName;
	
	public RoleDTO(){}
	public RoleDTO(int roleID, String roleName){
		this.setRoleID(roleID);
		this.setRoleName(roleName);
	}
	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}
	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}