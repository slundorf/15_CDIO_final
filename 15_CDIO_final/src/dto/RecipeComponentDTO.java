package dto;

import java.io.Serializable;

public class RecipeComponentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2433687775877282025L;
	private int recipeID;
	private int ingredientID;
	private double amount;
	private double tolerance;
	
	public RecipeComponentDTO(){}
	public RecipeComponentDTO(int recipeID, int ingredientID, double amount, double tolerance){
		this.setRecipeID(recipeID);
		this.setIngredientID(ingredientID);
		this.setAmount(amount);
		this.setTolerance(tolerance);
	}
	/**
	 * @return the ingredientID
	 */
	public int getIngredientID() {
		return ingredientID;
	}
	/**
	 * @param ingredientID the ingredientID to set
	 */
	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the tolerance
	 */
	public double getTolerance() {
		return tolerance;
	}
	/**
	 * @param tolerance the tolerance to set
	 */
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	/**
	 * @return the recipeID
	 */
	public int getRecipeID() {
		return recipeID;
	}
	/**
	 * @param recipeID the recipeID to set
	 */
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	
}