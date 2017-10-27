package shopping;

import shopping.discount.rules.*;
import shopping.models.Brand;
import shopping.models.Category;
import shopping.models.CategoryTreeNode;
import shopping.models.InventoryItem;

import java.util.*;

public class InventoryList {
    private CategoryTreeNode categoryRoot;
    private Map<Brand,Integer> brandDiscounts;
    private Map<Category,Integer> categoryDiscounts;
    private List<InventoryItem> items;

    public InventoryList(List<InventoryItem> inventoryItems){
        categoryRoot = buildCategoryHirarchy();
        brandDiscounts = buildBrandDiscount();
        categoryDiscounts = buildCategoryDiscount();
        items = inventoryItems;
    }

    private CategoryTreeNode buildCategoryHirarchy(){
        CategoryTreeNode root = new CategoryTreeNode("*");
        CategoryTreeNode mensWear = root.addChild("Men's wear");
        mensWear.addChild("Shirts");
        mensWear.addChild("Trousers");
        mensWear.addChild("Casuals");
        mensWear.addChild("Jeans");
        CategoryTreeNode womenWear = root.addChild("Women's wear");
        womenWear.addChild("Dresses");
        womenWear.addChild("Footwear");
        return root;
    }

    private Map<Brand,Integer> buildBrandDiscount(){
        HashMap<Brand, Integer> brandIntegerHashMap = new HashMap<>();
        brandIntegerHashMap.put(new Brand("Wrangler"), 10);
        brandIntegerHashMap.put(new Brand("Arrow"), 20);
        brandIntegerHashMap.put(new Brand("Vero Moda"), 60);
        brandIntegerHashMap.put(new Brand("Adidas"), 05);
        brandIntegerHashMap.put(new Brand("Provogue"), 20);
        return brandIntegerHashMap;

    }

    private Map<Category,Integer> buildCategoryDiscount(){
        HashMap<Category, Integer> categoryIntegerHashMap = new HashMap<>();
        categoryIntegerHashMap.put(new Category("Casuals"), 30);
        categoryIntegerHashMap.put(new Category("Jeans"), 20);
        categoryIntegerHashMap.put(new Category("Women's wear"), 50);
        return categoryIntegerHashMap;

    }

    public Integer priceForCustomerChoice(String... itemIds){
        int total = 0;
        for (String id: itemIds) {
            Optional<InventoryItem> inventoryItem = items.stream().filter(item -> item.getId().equals(id)).findFirst();
            inventoryItem.orElseThrow( () -> new IllegalArgumentException("could not find item with id"+ id));
            InventoryItem item = inventoryItem.get();
            List<DiscountRule> discountRules = Arrays.asList(
                    new BrandDiscountRule(item.getBrandName(), brandDiscounts),
                    new CategoryDiscountRule(item.getCategory(), categoryDiscounts),
                    new CategoryParentDiscountRule(item.getCategory(), categoryDiscounts, categoryRoot)
            );

            MaxDiscountRules maxDiscountRules = new MaxDiscountRules(discountRules);
            float maxDiscount = maxDiscountRules.getDiscountFactor();
            float discount = (item.getPrice() * maxDiscount) / 100;
            float discountedPrice = item.getPrice() - discount;
            total += discountedPrice;
        }
        return total;
    }
}
