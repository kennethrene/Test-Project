package wtest;

public class Tree {
    private Integer value;
    private Tree leftTree;
    private Tree rightTree;

    public Tree(Integer value, Tree leftTree, Tree rightTree) {
        this.value = value;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    public Tree(Integer value) {
        this.value = value;
    }

    public Tree getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(Tree leftTree) {
        this.leftTree = leftTree;
    }

    public Tree getRightTree() {
        return rightTree;
    }

    public void setRightTree(Tree rightTree) {
        this.rightTree = rightTree;
    }

    public Integer getValue() {
        return value;
    }
}
