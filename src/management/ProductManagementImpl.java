/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;

import java.util.ArrayList;
import java.util.List;
import entity.Product;
/**
 *
 * @author ADMIN
 */

public class ProductManagementImpl implements ProductManagement {
    private List<Product> productList;

    public ProductManagementImpl() {
        productList = new ArrayList<>();
    }

    @Override
    public void createProduct(Product product) {
        productList.add(product);
    }

    @Override
    public Product readProduct(int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void updateProduct(int productId, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setQuantity(updatedProduct.getQuantity());
                break;
            }
        }
    }

    @Override
    public void deleteProduct(int productId) {
        Product productToRemove = null;
        for (Product product : productList) {
            if (product.getId() == productId) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            productList.remove(productToRemove);
        }
    }
}
