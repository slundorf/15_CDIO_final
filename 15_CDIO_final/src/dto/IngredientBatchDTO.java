package dto;

import java.io.Serializable;

public class IngredientBatchDTO implements Serializable{

	private static final long serialVersionUID = -2455091258190346348L;
	private int ingredientBatchID;
	private int ingredientID;
	private double amount;
	private String supplier;
	
	public IngredientBatchDTO(){}
	public IngredientBatchDTO(int ingredientBatchID, int ingredientID, double amount, String supplier){
		this.setIngredientBatchID(ingredientBatchID);
		this.setIngredientID(ingredientBatchID);
		this.setAmount(amount);
		this.setSupplier(supplier);
	}
	/**
	 * @return the ingredientBatchID
	 */
	public int getIngredientBatchID() {
		return ingredientBatchID;
	}
	/**
	 * @param ingredientBatchID the ingredientBatchID to set
	 */
	public void setIngredientBatchID(int ingredientBatchID) {
		this.ingredientBatchID = ingredientBatchID;
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
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}
	/**
	 * @param supplier the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	
}