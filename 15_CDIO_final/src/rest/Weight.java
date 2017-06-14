package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import testData.FakeUserDAO;

@Path("weight")
public class Weight {

	IUserDAO IUD = new SerUserDAO();
	IRoleDAO IRD = new SerRoleDAO();
	IUserDAO FIUD = new FakeUserDAO();
	IIngredientDAO IID = new SerIngredientDAO();
	IIngredientBatchDAO IIBD = new SerIngredientBatchDAO();
	IRecipeDAO recipeDAO = new SerRecipeDAO();
	IRecipeComponentDAO recipecDAO = new SerRecipeComponentDAO();
	IProductBatchDAO IPB = new SerProductBatchDAO();
	IProductBatchComponentDAO IPBC = new SerProductBatchComponentDAO();
	IBusinessLayer IBL = new BusinessLayerImplementation(IUD, IRD, IID, IIBD, recipeDAO,IPB,IPBC,recipecDAO);
	int currentUserID;
	ArrayList<Integer> recipeComponentID = new ArrayList<Integer>();
	
	@POST @Path("login/{id}/{pass}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int loginUser(@PathParam("id") Integer id, @PathParam("pass") String pass) throws DALException {
		
		currentUserID = id;
		
		System.out.println(id);
		System.out.println(pass);
		
		return IBL.getUser(id).getRole().getRoleID();
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
	
	@POST @Path("recipe")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createRecipe(RecipeDTO recipe) throws DALException {
		
		for(int i=0; i<recipeComponentID.size(); i++) {
			recipe.addComponent(IBL.getRecipeComponent(recipeComponentID.get(i)));
		}
		IBL.createRecipe(recipe);
		
		return true;
	}
	
	@POST @Path("recipeComponent")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createRecipe(RecipeComponentDTO[] recipeComponent) throws DALException {
		
		for(int i=0; i<recipeComponent.length; i++) {
			IBL.createRecipeComponent(recipeComponent[i]);
			recipeComponentID.add(recipeComponent[i].getRecipeComponentID());
		}
		
		return true;
	}
	
//	@POST @Path("rc")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public boolean createRecipeComponent(RecipeComponentDTO rc, int recipeId) throws DALException {
//		RecipeDTO temp = IBL.getRecipe(recipeId);
//		temp.addComponent(rc);
//		IBL.updateRecipe(temp);
//		return true;
//	}
	
	@POST @Path("pb")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createProductBatch(ProductBatchDTO pb) throws DALException {
		
		IBL.createProductBatch(pb);
		
		return true;
	}
	
//	@POST @Path("pbc")
//	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public boolean createProductBatchComponent(ProductBatchComponentDTO pbc) throws DALException {;
//		IBL.updateProductBatch(pbc);
//		return true;
//	}

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
	
//	@GET @Path("getRoleID")
//	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//	public int getRoleID(int id) throws DALException {
//		
//		return IBL.getUser(id).getRole().getRoleID();
//	}
	
	@POST @Path("getUsr/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public UserDTO getUsr(@PathParam("id") Integer uid) throws DALException {
		
		return IBL.getUser(uid);
	}
	
	@GET @Path("currentUserID")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int getCurrentUserID() {
		return currentUserID;
	}
	
	@POST @Path("toggleStatus/{id}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean toggleStatus(@PathParam("id") int userID) throws DALException{
		UserDTO temp = IBL.getUser(userID);
		if(temp.isStatus()){
			temp.setStatus(false);
		}else{
			temp.setStatus(true);
		}
		IBL.updateUser(temp);
		
		return true;
		
	}
	
	@POST @Path("uu")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean updateUser(UserDTO data) throws DALException {		;

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

		IBL.updateUser(data);
		return true;
	}
	@GET @Path("getIngredients")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<IngredientDTO> getIngredients() throws DALException {
		return IBL.getIngredientList();
	}
	@GET @Path("getIngredientBatches")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException{
		return IBL.getIngredientBatchList();
	}
	@GET @Path("getPB")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ProductBatchDTO> getProductBatchList() throws DALException{
		return IBL.getProductBatchList();
	}
	@GET @Path("getPBC/{pbId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ProductBatchComponentDTO> getProductBatchComponentList(@PathParam("pbId") int pbId) throws DALException{
		return IBL.getProductBatchComponentList(pbId);
	}
	@GET @Path("getRecipeList")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<RecipeDTO> getRecipeList() throws DALException{
		return IBL.getRecipeList();
	}
	@GET @Path("getRecipeCompList/{recipeID}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<RecipeComponentDTO> getRecipeComponentList(@PathParam("recipeID") int recipeID) throws DALException{
		return IBL.getRecipeComponentList(recipeID);
	}
}
