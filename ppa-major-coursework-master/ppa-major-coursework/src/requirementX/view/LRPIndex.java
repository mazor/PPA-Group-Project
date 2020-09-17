package requirementX.view;


public class LRPIndex extends LabelReturnerPanel {
	
	private int index; 

	/**
	 * Takes values for title and index. Calls LabelReturnerPanel constructor with parameter title and assigns index as the index
	 * @param title
	 * @param index
	 */
	public LRPIndex(String title, int index) {
		
		super(title);
		this.index=index;

	}

	/**
	 * Returns the index
	 * @return index
	 */
	public int getIndex(){
		
		return index;
		
	}

	/**
	 * Sets the index
	 * @param index
	 */
	public void setIndex(int index){
		
		this.index=index;
	
	}
	
	
}
