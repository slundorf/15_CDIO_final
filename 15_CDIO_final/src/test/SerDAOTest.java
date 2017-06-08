package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
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

public class SerDAOTest {
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
		roleDB=new SerRoleDAO("TESTSerFiles/RoleDB.ser");
		userDB= new SerUserDAO("TESTSerFiles/UserDB.ser");
		
		ingredientDB = new SerIngredientDAO("TESTSerFiles/IngredientDB.ser");
		ingredientBDB = new SerIngredientBatchDAO("TESTSerFiles/IngredientBatchDB.ser");
		
		recipeDB=new SerRecipeDAO("TESTSerFiles/RecipeDB.ser");
		recipeCDB = new SerRecipeComponentDAO("TESTSerFiles/RecipeDB.ser");
		
		productBDB = new SerProductBatchDAO("TESTSerFiles/ProductBatchDB.ser");
		pbcDB=new SerProductBatchComponentDAO("TESTSerFiles/ProductBatchDB.ser");
		
		
		role = new RoleDTO(1,"Operator");
		usr1 = new UserDTO(11, "user1", "usr1", "01012000-1234", "User01", role, true );
		
		ingr1 = new IngredientDTO(1, "Tomat", "Tomatkompagniet");
		inb1 = new IngredientBatchDTO(1, ingr1.getIngredientID(), 2.5, ingr1.getSupplier());
		
		recipe = new RecipeDTO(1, "Flåede tomater");
		recipeComp = new RecipeComponentDTO(1, 1, 1.5, 0.5);
		
		pb = new ProductBatchDTO(1, recipe.getRecipeID(), recipe.getRecipeName(), "06-06-2017", "Not begun" );
		pbc= new ProductBatchComponentDTO(1, 1, ingr1.getIngredientName(), 1.5, 0.5, 0, 0, 1);
	}

	@Before
	public void setUp() throws Exception {
		roleDB.updateRole(role);
		userDB.updateUser(usr1);
		ingredientDB.updateIngredient(ingr1);
		ingredientBDB.updateIngredientBatch(inb1);
		
		recipe.addComponent(recipeComp);
		recipeDB.updateRecipe(recipe);
		recipeCDB.updateRecipeComponent(recipeComp);
		
		pb.addComponent(pbc);
		productBDB.updateProductBatch(pb);
		pbcDB.updateProductBatchComponent(pbc);
	}

	@After
	public void tearDown() throws Exception {
		role = new RoleDTO(1,"Operator");
		usr1 = new UserDTO(11, "user1", "usr1", "01012000-1234", "User01", role, true );
		
		ingr1 = new IngredientDTO(1, "Tomat", "Tomatkompagniet");
		inb1 = new IngredientBatchDTO(1, ingr1.getIngredientID(), 2.5, ingr1.getSupplier());
		
		recipe = new RecipeDTO(1, "Flåede tomater");
		recipeComp = new RecipeComponentDTO(1, 1, 1.5, 0.5);
		
		pb = new ProductBatchDTO(1, recipe.getRecipeID(), recipe.getRecipeName(), "06-06-2017", "Not begun" );
		pbc= new ProductBatchComponentDTO(1, 1, ingr1.getIngredientName(), 1.5, 0.5, 0, 0, 1);
	}

	
	
	@Test
	public void testSerRoleDAO() throws DALException{
		//READ
		assertEquals(roleDB.getRole(1).getClass(),role.getClass());
		assertEquals(roleDB.getRole(1).getRoleID(),role.getRoleID());
		assertEquals(roleDB.getRole(1).getRoleName(),role.getRoleName());
		//UPDATE
		role.setRoleName("updatedName");
		roleDB.updateRole(role);
		assertEquals(roleDB.getRole(1).getRoleName(),role.getRoleName());
	}
	@Test
	public void testSerUserDAO() throws DALException {
		//READ
		assertEquals(userDB.getUser(11).getUserID(),usr1.getUserID());
		assertEquals(userDB.getUser(11).getUserName(), usr1.getUserName());
		assertEquals(userDB.getUser(11).getCpr(),usr1.getCpr());
		assertEquals(userDB.getUser(11).getIni(),usr1.getIni());
		assertEquals(userDB.getUser(11).getPassword(),usr1.getPassword());
		assertEquals(userDB.getUser(11).getRole().getRoleID(),usr1.getRole().getRoleID());
		assertEquals(userDB.getUser(11).getRole().getRoleName(),usr1.getRole().getRoleName());
		
		//UPDATE
		usr1.setUserName("updatedName");
		userDB.updateUser(usr1);
		assertTrue(userDB.getUser(11).getUserName().equals(usr1.getUserName()));
	}
	@Test
	public void testSerIngredientDAO() throws DALException{
		//READ
		assertEquals(ingredientDB.getIngredient(1).getIngredientID(),ingr1.getIngredientID());
		assertEquals(ingredientDB.getIngredient(1).getIngredientName(),ingr1.getIngredientName());
		assertEquals(ingredientDB.getIngredient(1).getSupplier(),ingr1.getSupplier());
		//UPDATE
		ingr1.setIngredientName("updatedName");
		ingredientDB.updateIngredient(ingr1);
		assertEquals(ingredientDB.getIngredient(1).getIngredientName(),ingr1.getIngredientName());
	}

}
