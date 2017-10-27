package shopping.discount.rules;

import java.util.List;

public class MaxDiscountRules implements DiscountRule {

    private final List<DiscountRule> rules;

    public MaxDiscountRules(List<DiscountRule> rules) {
        this.rules = rules;
    }

    @Override
    public Integer getDiscountFactor() {
        Integer max = 0;
        for (DiscountRule r : rules) {
            max = Math.max(max, r.getDiscountFactor());
        }
        return max;
    }
}
