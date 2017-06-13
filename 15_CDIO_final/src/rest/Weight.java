package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import businessLayer.BusinessLayerImplementation;
import businessLayer.IBusinessLayer;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchComponentDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeComponentDAO;
import interfaces.IRecipeDAO;
import interfaces.IRoleDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerIngredientDAO;
import serDAO.SerProductBatchComponentDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerRecipeComponentDAO;
import serDAO.SerRecipeDAO;
import serDAO.SerRoleDAO;
import serDAO.SerUserDAO;

@Path("weight")
public class Weight {

	IUserDAO IUD = new SerUserDAO();
	IRoleDAO IRD = new SerRoleDAO();
	IIngredientDAO IID = new SerIngredientDAO();
	IIngredientBatchDAO IIBD = new SerIngredientBatchDAO();
	IRecipeDAO recipeDAO = new SerRecipeDAO();
	IRecipeComponentDAO recipecDAO = new SerRecipeComponentDAO();
	IProductBatchDAO IPB = new SerProductBatchDAO();
	IProductBatchComponentDAO IPBC = new SerProductBatchComponentDAO();
	IBusinessLayer IBL = new BusinessLayerImplementation(IUD, IRD, IID, IIBD, recipeDAO,IPB,IPBC,recipecDAO);
	
	@POST @Path("login")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int loginUser(String id) throws DALException {
		System.out.println(id);
		return 1;
	}
	
	@POST @Path("cu")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createUser(UserDTO data) throws DALException {		;

		switch(data.getRole().getRoleID()){
			case 1: 
				data.getRole().setRoleName("Administrator");
				break;
			case 2:
				data.getRole().setRoleName("Pharmacist");
				break;
			case 3: 
				data.getRole().setRoleName("Foreman");
				break;
			case 4: 
				data.getRole().setRoleName("Operator");
				break;
		}

		IBL.createUser(data);
		return true;
	}
	
	@POST @Path("recept")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createRecipe() {
		return true;
	}
	
	@POST @Path("rc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createRecipeComponent(RecipeComponentDTO rc, int recipeId) throws DALException {
		RecipeDTO temp = IBL.getRecipe(recipeId);
		temp.addComponent(rc);
		IBL.updateRecipe(temp);
		return true;
	}
	
	@POST @Path("pb")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createProductBatch(ProductBatchDTO pb) throws DALException {
		
		IBL.createProductBatch(pb);
		
		return true;
	}
	
	@POST @Path("pbc")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createProductBatchComponent(ProductBatchComponentDTO pbc, int pbId) throws DALException {
		ProductBatchDTO temp = IBL.getProductBatch(pbId);
		temp.addComponent(pbc);
		IBL.updateProductBatch(temp);
		return true;
	}

	@POST @Path("ci")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createIngredient(IngredientDTO ing) throws DALException {
		
		IBL.createIngredient(ing);
		
		return true;
	}
	
	@POST @Path("ib")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createIngredientBatch(IngredientBatchDTO ib) throws DALException {
		
		IBL.createIngredientBatch(ib);
		
		return true;
	}
	
	@GET @Path("getUsrs")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserDTO> getUsrs() throws DALException {
		
		return IBL.getUserList();
	}
	
	@GET @Path("getRoleID")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int getRoleID(int id) throws DALException {
		
		return IBL.getUser(id).getRole().getRoleID();
	}
	
	@GET @Path("getUsr")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO getUsr(int id) throws DALException {
		
		return IBL.getUser(id);
	}
	
}
