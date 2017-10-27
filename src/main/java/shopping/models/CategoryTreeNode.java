package shopping.models;


import java.util.ArrayList;
import java.util.List;

public class CategoryTreeNode {

    String data;
    CategoryTreeNode parent;

    public String getData() {
        return data;
    }

    public List<CategoryTreeNode> getChildren() {
        return children;
    }

    List<CategoryTreeNode> children;

    public CategoryTreeNode getParent() {
        return parent;
    }

    public CategoryTreeNode(String data) {
        this.data = data;
        this.children = new ArrayList<CategoryTreeNode>();
    }

    public CategoryTreeNode addChild(String child) {
        CategoryTreeNode childNode = new CategoryTreeNode(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public CategoryTreeNode findCategoryTreeNode(String data){
        if(this.data.equals(data)) return this;
        for(CategoryTreeNode child: children){
            CategoryTreeNode match = child.findCategoryTreeNode(data);
            if(match != null) return match;
        }

        return null;
    }

}

