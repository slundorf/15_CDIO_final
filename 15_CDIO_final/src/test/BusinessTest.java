package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import businessLayer.BusinessLayerImplementation;
import businessLayer.IBusinessLayer;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.*;
import serDAO.*;
import testData.FakeUserDAO;

public class BusinessTest {
	public static IRoleDAO roleDB;
	public static IUserDAO userDB;
	public static IIngredientDAO ingredientDB;
	public static IIngredientBatchDAO ingredientBDB;
	public static IRecipeDAO recipeDB;
	public static IRecipeComponentDAO recipeCDB;
	public static IProductBatchDAO productBDB;
	public static IProductBatchComponentDAO pbcDB;
	
	public static RoleDTO role;
	public static UserDTO usr1;
	public static IngredientDTO ingr1;
	public static IngredientBatchDTO inb1;
	public static RecipeDTO recipe;
	public static RecipeComponentDTO recipeComp;
	public static ProductBatchDTO pb;
	public static ProductBatchComponentDTO pbc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDB= new FakeUserDAO();
		role = new RoleDTO(1,"Operator");
		usr1 = new UserDTO(11, "user1", "usr1", "01012000-1234", "User01", role, true );
	}
	@After
	public void tearDown() throws Exception {
		role = new RoleDTO(1,"Operator");
		usr1 = new UserDTO(11, "user1", "usr1", "01012000-1234", "User01", role, true );
	}

		
	@Test
	public void test() throws DALException {
	
		
		
		/**
		 * Check password
		 */
		
		/**
		 * create password
		 */
		
		
		/**
		 * check cpr
		 */
		
		/**
		 * check user
		 */
		
		/**
		 * check updated user
		 */
		
	}

}
