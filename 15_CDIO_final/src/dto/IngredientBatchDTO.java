package dto;

public class IngredientBatchDTO {
	private int ingredientBatchID;
	private int ingredientID;
	private double amount;
	
	public IngredientBatchDTO(){}
	public IngredientBatchDTO(int ingredientBatchID, int ingredientID, double amount){
		this.setIngredientBatchID(ingredientBatchID);
		this.setIngredientID(ingredientBatchID);
		this.setAmount(amount);
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
	
	
}