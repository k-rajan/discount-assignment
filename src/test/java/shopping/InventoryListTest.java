package shopping;

import org.junit.Test;
import shopping.InventoryList;
import shopping.models.Brand;
import shopping.models.Category;
import shopping.models.InventoryItem;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class InventoryListTest {
    @Test
    public void priceForCustomerChoice() throws Exception {
        ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
        inventoryItems.add(new InventoryItem( "1", new Brand("Arrow"),new Category("Shirts"),800));
        inventoryItems.add(new InventoryItem( "2", new Brand("Vero Moda"),new Category("Dresses"),1400));
        inventoryItems.add(new InventoryItem( "3", new Brand("Provogue"),new Category("Footwear"),1800));
        inventoryItems.add(new InventoryItem( "4", new Brand("Wrangler"),new Category("Jeans"),2200));
        inventoryItems.add(new InventoryItem( "5", new Brand("UCB"),new Category("Shirts"),1500));

        InventoryList inventoryList = new InventoryList(inventoryItems);
        Integer priceForCustomerChoice = inventoryList.priceForCustomerChoice("1","2","3","4");
        assertThat(priceForCustomerChoice, is(3860));

        Integer priceForAnotherChoice = inventoryList.priceForCustomerChoice("1", "5");
        assertThat(priceForAnotherChoice, is(2140));
    }

    @Test
    public void addingItemAsCsv() throws Exception {
        List<InventoryItem> items = new ArrayList<>();

        items.add(InventoryItem.fromCsv("1,Arrow,Shirts,800"));
        items.add(InventoryItem.fromCsv("2,Vero Moda,Dresses,1400"));
        items.add(InventoryItem.fromCsv("3, Provogue,Footwear,1800"));
        items.add(InventoryItem.fromCsv("4, Wrangler,Jeans,2200"));
        items.add(InventoryItem.fromCsv("5, UCB,Shirts,1500"));

        InventoryList inventoryList = new InventoryList(items);

        Integer priceForCustomerChoice = inventoryList.priceForCustomerChoice("1,2,3,4".split(","));
        assertThat(priceForCustomerChoice, is(3860));
    }
}