package shopping.models;

public class InventoryItem {
    private final String id;
    private final Brand brandName;
    private final Category category;
    private final float price;

    public InventoryItem(String id, Brand brandName, Category category, float price) {
        this.id = id;
        this.brandName = brandName;
        this.category = category;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public Brand getBrandName() {
        return brandName;
    }

    public Category getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public static InventoryItem fromCsv(String csv){
        String[] split = csv.split(",");
        if(split.length<4) throw new IllegalArgumentException("Inventory Item should be in format id,brand,category,price. found "+csv);
        return  new InventoryItem(split[0].trim(), new Brand(split[1]), new Category(split[2]), Float.parseFloat(split[3]));
    }
}
