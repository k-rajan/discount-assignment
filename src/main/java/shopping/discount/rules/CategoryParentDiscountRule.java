package shopping.discount.rules;

import shopping.models.CategoryTreeNode;
import shopping.models.Category;

import java.util.Map;

public class CategoryParentDiscountRule implements DiscountRule {
    private final Category category;
    private final Map<Category,Integer> categoryDiscountLookup;
    private final CategoryTreeNode categoryRoot;

    public CategoryParentDiscountRule(Category category, Map<Category, Integer> categoryDiscountLookup, CategoryTreeNode categoryRoot) {
        this.category = category;
        this.categoryDiscountLookup = categoryDiscountLookup;
        this.categoryRoot = categoryRoot;
    }

    private Integer maxAncestorDiscount(){
        Integer max = 0;
        CategoryTreeNode matchedNode = categoryRoot.findCategoryTreeNode(category.getName());
        if(matchedNode == null) return max;
        CategoryTreeNode parent = matchedNode.getParent();
        while (parent != null){
            Integer parentDiscount = categoryDiscountLookup.getOrDefault(new Category(parent.getData()), 0);
            max = Math.max(max, parentDiscount);
            parent = parent.getParent();
        }
        return max;
    }

    @Override
    public Integer getDiscountFactor() {
        return maxAncestorDiscount();
    }
}
