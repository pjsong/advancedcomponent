package ruking.dto;

import java.io.Serializable;

public class CategoryDTO  implements Serializable{
	private String id;
	private String category = "";;
	private String subcategory = "";
	public String getId() {
		return id;
	}
	public void setId(String string) {
		this.id = string;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	};

}
