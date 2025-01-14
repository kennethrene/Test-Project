package test;

public class TestTree {
    public static void main(String... args) {
        TestTree testTree = new TestTree();

        if (testTree.compare(testTree.createTree())) {
            System.out.println("Mirror");
        } else {
            System.out.println("No Mirror");
        }
    }

    private boolean compare(Tree tree) {
        return tree.getLeftTree().equals(tree.getRightTree());
    }

    private Tree createTree() {
        Tree tree = new Tree(1);

        Tree leftTree = new Tree(2);
        Tree rightTree = new Tree(2);

        tree.setLeftTree(leftTree);
        tree.setRightTree(rightTree);

        Tree leftTreeLeft = new Tree(3);
        Tree leftTreeRight = new Tree(4);
        leftTree.setLeftTree(leftTreeLeft);
        leftTree.setLeftTree(leftTreeRight);

        Tree rightTreeLeft = new Tree(3);
        Tree rightTreeRight = new Tree(4);
        rightTree.setLeftTree(rightTreeLeft);
        rightTree.setLeftTree(rightTreeRight);

        return tree;
    }
}
