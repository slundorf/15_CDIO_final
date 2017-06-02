package dto;

public class IngredientDTO {
	private int ingredientID;
	private String ingredientName;
	private String supplier;
	
	public IngredientDTO(){}
	public IngredientDTO(int ingredientID, String ingredientName, String supplier){
		this.setIngredientID(ingredientID);
		this.setIngredientName(ingredientName);
		this.setSupplier(supplier);
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
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}
	/**
	 * @param ingredientName the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
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