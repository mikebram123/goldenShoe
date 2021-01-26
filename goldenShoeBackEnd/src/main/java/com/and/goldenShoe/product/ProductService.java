package com.and.goldenShoe.product;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.and.goldenShoe.productSize.SizeDAO;
import com.and.goldenShoe.productSize.SizeEntity;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentDAO;
import com.and.goldenShoe.productSizeAssignment.ProductSizeAssignmentEntity;

@Component
public class ProductService implements ProductAPI {

	@Autowired
	ProductDAO proDAO;

	@Autowired
	ProductSizeAssignmentDAO assDAO;

	@Autowired
	SizeDAO sizeDAO;



	/*
	 * Joins product and productSizeAssignment together
	 */
	public ProductEntity joinProductAssigned(int productID, int assignedID) {
		ProductEntity product = proDAO.findById(productID).get();
		ProductSizeAssignmentEntity assigned = assDAO.findById(assignedID).get();

		assigned.setLinkedProduct(product);
		product.getAssignedProducts().add(assigned);

		assDAO.save(assigned);
		proDAO.save(product);

		return product;

	}

	/*
	 * Joins size and productSizeAssignment together
	 */
	public SizeEntity joinSizeAssigned(int sizeID, int assignedID) {
		SizeEntity size = sizeDAO.findById(sizeID).get();
		ProductSizeAssignmentEntity assigned = assDAO.findById(assignedID).get();

		assigned.setLinkedSize(size);
		size.getAssignedSize().add(assigned);

		assDAO.save(assigned);
		sizeDAO.save(size);

		return size;	
	}

	/*
	 * Creates a new product with a specific size and quantity
	 * returns the new product
	 * @see com.and.goldenShoe.product.ProductAPI#addProduct(com.and.goldenShoe.product.ProductEntity, double, int)
	 */
	public ProductEntity addProduct(ProductEntity newProduct, double size, int quantity) {
		SizeEntity sizeE = sizeDAO.findSize(size);
		System.out.println(sizeE);
		ProductSizeAssignmentEntity prodS = new ProductSizeAssignmentEntity();
		System.out.println(prodS);

		prodS.setQuantity(quantity);
		proDAO.save(newProduct);
		assDAO.save(prodS);

		joinProductAssigned(newProduct.getProductID(), prodS.getProduct_size_assignmentID());
		joinSizeAssigned(sizeE.getSizeID(), prodS.getProduct_size_assignmentID());


		return newProduct;
	}

	/*
	 * listAll products in the database
	 * @see com.and.goldenShoe.product.ProductAPI#listAllProducts()
	 */
	@Override
	public Set<ProductEntity> listAllProducts() {
		Iterable<ProductEntity> prods = proDAO.findAll();
		return availableShoes(prods);
	}

	/*
	 * Returns product of a specific brand
	 * @see com.and.goldenShoe.product.ProductAPI#findByBrand(com.and.goldenShoe.product.ProductBrands)
	 */
	@Override
	public Set<ProductEntity> findByBrand(ProductBrands brand) {
		Iterable<ProductEntity> prods = proDAO.findShoesByBrand(brand);
		return availableShoes(prods);
	}

	/*
	 * Returns shoes with at least 1 quantity
	 */
	public Set<ProductEntity> availableShoes(Iterable<ProductEntity> products){
		Stream<ProductEntity> prods = StreamSupport.stream(products.spliterator(), true);
		return prods.filter(prod-> (isProductAvailable(prod)==true)).collect(Collectors.toSet());	

	}

	/*
	 * Returns true if a product has at least 1 quantity
	 */
	private boolean isProductAvailable(ProductEntity prod) {
		Set<ProductSizeAssignmentEntity> sizeSet = prod.getAssignedProducts();
		Stream<ProductSizeAssignmentEntity> sizeStream = sizeSet.stream();
		return sizeStream.anyMatch(size -> size.getQuantity()>0);
	}

	/*
	 * Given a productID will upload new sizes and quantity of that product
	 * @see com.and.goldenShoe.product.ProductAPI#UpdateSizes(int, double, int)
	 * if product is currently in database with the size will update that record instead of making a new entry
	 */
	@Override
	public ProductEntity UpdateSizes(int productID, double size, int quantity) {
		ProductEntity selectedProd = proDAO.findById(productID).get();
		Iterable<ProductSizeAssignmentEntity> prodSizes = selectedProd.getAssignedProducts();
		boolean isUpdated = true;
		for (ProductSizeAssignmentEntity ps : prodSizes) {
			if (ps.getLinkedSize().getSize() == size) {
				ps.setQuantity(ps.getQuantity()+quantity);
				assDAO.save(ps);
				isUpdated = false;
				break;
			}
		}
		if(isUpdated) {
			addProduct(selectedProd, size, quantity);
		}
		return selectedProd;  
	}

	/*
	 * Returns a set of shoes in a specific size
	 * @see com.and.goldenShoe.product.ProductAPI#findBySize(int)
	 */
	@Override
	public Set<ProductEntity> findBySize(int size) {
		Iterable<ProductEntity> prods = proDAO.findAll();
		Stream<ProductEntity> prodStream = StreamSupport.stream(prods.spliterator(), true);
		return prodStream.filter(prod->(doesSizeExist(prod, size)==true)).collect(Collectors.toSet());
	}

	/*
	 * Returns true if a product is in a specifc size specified
	 */
	private boolean doesSizeExist(ProductEntity prod, int size) {
		Set<ProductSizeAssignmentEntity> prods = prod.getAssignedProducts();
		Stream<ProductSizeAssignmentEntity> productSizes = prods.stream();
		return productSizes.anyMatch(shoeSize->shoeSize.getQuantity()>0 && shoeSize.getLinkedSize().getSize()==size);
	}

	@Override
	public ProductEntity addProduct(int productID) {
		return proDAO.findById(productID).get();
	}

	/*
	 * returns a set of sizes that are available in that shoe
	 * @see com.and.goldenShoe.product.ProductAPI#getSizes(int)
	 */
	@Override
	public Set<Double> getSizes(int productID) {
		ProductEntity product = proDAO.findById(productID).get();
		Set<ProductSizeAssignmentEntity> sizes = product.getAssignedProducts();
		Set<Double> availSizes = new HashSet<>();
		for (ProductSizeAssignmentEntity a : sizes) {
			if(a.getQuantity()>0) {
				availSizes.add(a.getLinkedSize().getSize());
			}
		}
		return availSizes;
	}
	
	

}








