package entity;


import java.util.ArrayList;
import java.util.List;
import utils.MyUtils;

public class ShopOwner extends User {
    private String shopName;
    private final List<Product> products;
    private static final String shopListFile = "src\\data\\shopList.txt";

    public ShopOwner(String username, String password, String shopName) {
        super(username, password, shopName);
        this.shopName = shopName;
        this.products = new ArrayList<>();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
//    public Shop getShop() {
//        return shop;
//    }

    @Override
    public String toString() {
        return "ShopOwner{" + "shopName=" + shopName + '}';
    }

}