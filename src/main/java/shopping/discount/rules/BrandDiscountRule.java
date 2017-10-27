package shopping.discount.rules;

import shopping.models.Brand;

import java.util.Map;

public class BrandDiscountRule implements DiscountRule {
    private final Brand brand;
    private final Map<Brand,Integer> brandDiscountLookup;

    public BrandDiscountRule(Brand brand, Map<Brand, Integer> brandDiscountLookup) {
        this.brand = brand;
        this.brandDiscountLookup = brandDiscountLookup;
    }

    @Override
    public Integer getDiscountFactor() {
        return brandDiscountLookup.getOrDefault(brand, 0);
    }
}
