package shopping.discount.rules;

import shopping.models.Category;

import java.util.Map;

public class CategoryDiscountRule implements DiscountRule {
    private final Category category;
    private final Map<Category,Integer> categoryDiscountLookup;

    public CategoryDiscountRule(Category category, Map<Category, Integer> categoryDiscountLookup) {
        this.category = category;
        this.categoryDiscountLookup = categoryDiscountLookup;
    }

    @Override
    public Integer getDiscountFactor() {
        return categoryDiscountLookup.getOrDefault(category, 0);
    }
}
