import shopping.InventoryList;
import shopping.models.InventoryItem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input:");
        Integer numberOfItems = Integer.parseInt(br.readLine());
        List<InventoryItem> inventoryItems = new ArrayList<>();

        for(int i=0; i<numberOfItems; i++){
            String itemAsCsv = br.readLine();
            inventoryItems.add(InventoryItem.fromCsv(itemAsCsv));
        }

        br.readLine(); //empty

        Integer numberOfChoices = Integer.parseInt(br.readLine());

        List<String[]> choices = new ArrayList<>();
        for(int i=0; i<numberOfChoices; i++){
            String choiceAsCsv = br.readLine();
            String[] choicesAsList = choiceAsCsv.split(",");
            choices.add(choicesAsList);
        }

        System.out.println("Output:");
        for (String[] customerChoices: choices) {
            InventoryList inventoryList = new InventoryList(inventoryItems);
            Integer dicountedPrice = inventoryList.priceForCustomerChoice(customerChoices);
            System.out.println(dicountedPrice);
        }
    }
}
