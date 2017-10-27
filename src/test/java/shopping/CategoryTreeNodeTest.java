package shopping;

import org.junit.Test;
import shopping.models.CategoryTreeNode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class CategoryTreeNodeTest {
    @Test
    public void addChild() throws Exception {
        CategoryTreeNode root = new CategoryTreeNode("root");
        CategoryTreeNode mensWear = root.addChild("Mens Wear");
        mensWear.addChild("Trouser");

        assertThat(root.getChildren().get(0).getData(), is("Mens Wear"));
        assertThat(root.getChildren().get(0).getChildren().get(0).getData(), is("Trouser"));
    }

    @Test
    public void findCategoryTreeNode() throws Exception {
        CategoryTreeNode root = new CategoryTreeNode("root");
        CategoryTreeNode mensWear = root.addChild("Mens Wear");
        mensWear.addChild("Trouser");

        CategoryTreeNode trouser = root.findCategoryTreeNode("Trouser");
        assertThat(trouser.getData(), is("Trouser"));
        assertThat(trouser.getParent().getData(), is("Mens Wear"));
        assertThat(trouser.getParent().getParent().getData(), is("root"));

    }

}