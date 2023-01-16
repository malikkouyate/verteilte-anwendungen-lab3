package de.berlin.htw.boundary.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Alexander Stanik [stanik@htw-berlin.de]
 */
public class Item {

	@Size(max = 255)
    private String productName;

	@Size(min = 7, max = 7)
	@Pattern(regexp = "\\d-\\d-\\d-\\d-\\d-\\d")
    private String productId;

	@Max(10)
    private Integer count;

	@Min(10)
	@Max(100)
    private Float price;

    public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
