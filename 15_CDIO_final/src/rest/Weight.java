package rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import businessLayer.BusinessLayerImplementation;
import businessLayer.IBusinessLayer;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IRoleDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerIngredientDAO;
import serDAO.SerRoleDAO;
import serDAO.SerUserDAO;

@Path("weight")
public class Weight {

	IUserDAO IUD = new SerUserDAO();
	IRoleDAO IRD = new SerRoleDAO();
	IIngredientDAO IID = new SerIngredientDAO();
	IIngredientBatchDAO IIBD = new SerIngredientBatchDAO();
	IBusinessLayer IBL = new BusinessLayerImplementation(IUD, IRD, IID, IIBD);
	
	@POST @Path("login")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean loginUser() {
		return true;
	}
	
	@POST @Path("cu")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createUser(UserDTO user) throws DALException {		

		IBL.createUser(user);
		
		return true;
		
	}
	
	@POST @Path("recept")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createRecept() {
		return true;
	}
	
	@POST @Path("pb")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public boolean createProduct(ProductBatchDTO pb) throws DALException {
		
		IBL.createProductBatch(pb);
		
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
	
	@GET @Path("getUsr")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<UserDTO> getUsr() throws DALException {
		return IBL.getUserList();
	}
	
}
