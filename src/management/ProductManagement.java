/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package management;
import entity.Product;
/**
 *
 * @author ADMIN
 */
public interface ProductManagement {
    void createProduct(Product product);
    Product readProduct(int productId);
    void updateProduct(int productId, Product product);
    void deleteProduct(int productId);
}
