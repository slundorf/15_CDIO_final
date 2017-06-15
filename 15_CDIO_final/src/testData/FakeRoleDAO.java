///**
// * 
// */
//package testData;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import dto.RoleDTO;
//import exceptions.DALException;
//import interfaces.IRoleDAO;
//
///**
// * @author s136335
// *
// */
//public class FakeRoleDAO implements IRoleDAO {
//	
//	
//	
//	List<RoleDTO> fakeRoleList;
//	RoleDTO role1, role2, role3, role4;
//
//	
//	
//	public FakeRoleDAO() {
//		// TODO Auto-generated constructor stub
//		fakeRoleList = new ArrayList<RoleDTO>();
//		 role1 = new RoleDTO(1, "Administator");
//		 role2 = new RoleDTO(2, "Pharmacist");
//		 role3 = new RoleDTO(3, "Foreman");
//		 role4 = new RoleDTO(4, "Operator");
//		 fakeRoleList.add(role1);
//		 fakeRoleList.add(role2);
//		 fakeRoleList.add(role3);
//		 fakeRoleList.add(role4);
//	}
//	
//	public RoleDTO getRole(int roleID) throws DALException {
//		
//		for(int i = 0; i < fakeRoleList.size(); i++) {
//			if (fakeRoleList.get(i).getRoleID() == roleID) {
//				return fakeRoleList.get(i);
//			}
//		}
//		throw new DALException("No roles have been found with id: " + roleID);
//	}
//
//	@Override
//	public List<RoleDTO> getRoleList() throws DALException {
//		
//		return fakeRoleList;
//	}
//
//	@Override
//	public void createRole(RoleDTO Role) throws DALException {
//		
//		for(int i=0;i<fakeRoleList.size();i++)
//			if(fakeRoleList.get(i).getRoleID()==Role.getRoleID())
//				throw new DALException("A role with ID "+Role.getRoleID()+" already exists");
//		fakeRoleList.add(Role);
//	
//	}
//	@Override
//	public void updateRole(RoleDTO Role) throws DALException {
//		
//		for(int i = 0; i< fakeRoleList.size(); i++) {
//			if(Role.getRoleID() == fakeRoleList.get(i).getRoleID()) {
//				fakeRoleList.remove(i);
//				fakeRoleList.add(Role);
//			}
//		}
//	}
//}
