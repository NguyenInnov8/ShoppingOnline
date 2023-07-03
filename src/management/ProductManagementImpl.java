/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import java.util.ArrayList;
import java.util.List;
import entity.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductManagementImpl implements ProductManagement {
    private final List<Product> productList;
    private final Map<String, List<Integer>> productRatings;


    public ProductManagementImpl() {
        productList = new ArrayList<>();
        productRatings = new HashMap<>();
    }

    @Override
    public void createProduct(Product product) {
        productList.add(product);
    }

    @Override
    public Product readProduct(int productId) {
        for (Product product : productList) {
            if (product.getProductID == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateProduct(int productId, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getProductID == productId) {
                product.setProductName(updatedProduct.getProductName());
                product.setQuantity(updatedProduct.getQuantity());
                product.setPrice(updatedProduct.getPrice());
                product.setSoldQuantity(updatedProduct.getSoldQuantity());
                product.setShopId(updatedProduct.getShopId());
                product.setRating(updatedProduct.getRating());
                break;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        String stringProductId = Integer.toString(productId);
        Product productToRemove = null;
        for (Product product : productList) {
            if (product.getProductID == productId) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
            productRatings.remove(stringProductId);
        }
    }
    
    
    public void rateProduct(String productId, int rating) {
        List<Integer> ratings = productRatings.getOrDefault(productId, new ArrayList<>());
        ratings.add(rating);
        productRatings.put(productId, ratings);
    }

    @Override
    public double getProductAverageRating(String productId) {
        List<Integer> ratings = productRatings.getOrDefault(productId, new ArrayList<>());
        if (ratings.isEmpty()) {
            return 0;
        } else {
            int sum = 0;
            for (int rating : ratings) {
                sum += rating;
            }
            return (double) sum / ratings.size();
        }
    }
   
}
