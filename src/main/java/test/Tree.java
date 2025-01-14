package test;

import java.util.Objects;

public class Tree {
    private Integer value;
    private Tree leftTree;
    private Tree rightTree;

    public Tree(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(value, tree.value) && Objects.equals(leftTree, tree.leftTree) && Objects.equals(rightTree, tree.rightTree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, leftTree, rightTree);
    }
}
