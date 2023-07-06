///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package management;
//
//import java.util.ArrayList;
//import java.util.List;
//import entity.Product;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class ProductManagementImpl implements ProductManagement {
//    private final List<Product> productList;
//    private final Map<String, List<Integer>> productRatings;
//
//
//    public ProductManagementImpl() {
//        productList = new ArrayList<>();
//        productRatings = new HashMap<>();
//    }
//
//    @Override
//    public void createProduct(Product product) {
//         if (product != null) {
//            productList.add(product);
//            System.out.println("Product created successfully.");
//        } else {
//            System.out.println("Invalid product. Creation failed.");
//        }
//    }
//
//    @Override
//    public Product readProduct(int productId) {
//         for (Product product : productList) {
//            if (product.getProductID == productId) {
//                return product;
//            }
//        }
//        System.out.println("Product not found.");
//        return null;
//    }
//
//    @Override
//    public void updateProduct(int productId, Product updatedProduct) {
//       boolean found = false;
//        for (Product product : productList) {
//            if (product.getProductID == productId) {
//                product.setProductName(updatedProduct.getProductName());
//                product.setQuantity(updatedProduct.getQuantity());
//                product.setPrice(updatedProduct.getPrice());
//                product.setSoldQuantity(updatedProduct.getSoldQuantity());
//                product.setShopId(updatedProduct.getShopId());
//                product.setRating(updatedProduct.getRating());
//                found = true;
//                System.out.println("Product updated successfully.");
//                break;
//            }
//        }
//        if (!found) {
//            System.out.println("Product not found. Update failed.");
//        }
//    }
//
//    @Override
//    public void deleteProduct(int productId) {
//        boolean removed = false;
//        Iterator<Product> iterator = productList.iterator();
//        while (iterator.hasNext()) {
//            Product product = iterator.next();
//            if (product.getProductID == productId) {
//                iterator.remove();
//                removed = true;
//                System.out.println("Product deleted successfully.");
//                break;
//            }
//        }
//        if (!removed) {
//            System.out.println("Product not found. Deletion failed.");
//        } else {
//            String stringProductId = Integer.toString(productId);
//            productRatings.remove(stringProductId);
//        }
//    }
//    
//     public boolean isProductExists(int productId) {
//        for (Product product : productList) {
//            if (product.getProductID == productId) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void printProductList() {
//        if (productList.isEmpty()) {
//            System.out.println("No products found.");
//        } else {
//            System.out.println("Product List:");
//            for (Product product : productList) {
//                System.out.println(product);
//            }
//        }
//    }
//    
//    public void rateProduct(String productId, int rating) {
//        List<Integer> ratings = productRatings.getOrDefault(productId, new ArrayList<>());
//        ratings.add(rating);
//        productRatings.put(productId, ratings);
//    }
//
//    @Override
//    public double getProductAverageRating(String productId) {
//        List<Integer> ratings = productRatings.getOrDefault(productId, new ArrayList<>());
//        if (ratings.isEmpty()) {
//            return 0;
//        } else {
//            int sum = 0;
//            for (int rating : ratings) {
//                sum += rating;
//            }
//            return (double) sum / ratings.size();
//        }
//    }
//   
//}
