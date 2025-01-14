package wtest;

public class TestTree {
    public static void main(String ... args) {
        TestTree test = new TestTree();

        Tree tree1 = test.createTree1();
        Tree tree2 = test.createTree2();

        if (tree1.getValue().equals(tree2.getValue())) {
            System.out.print(test.equalTree(tree1, tree2));
        } else {
            System.out.print(false);
        }
    }

    private boolean equalTree(Tree tree1, Tree tree2) {
        Integer leftValueT1 = tree1.getLeftTree() != null? tree1.getLeftTree().getValue() : -1;
        Integer rightValueT1 = tree1.getRightTree() != null? tree1.getRightTree().getValue() : -1;

        Integer leftValueT2 = tree2.getLeftTree() != null ? tree2.getLeftTree().getValue() : -1;
        Integer rightValueT2 = tree2.getRightTree() != null ? tree2.getRightTree().getValue() : -1;

        if (leftValueT1.equals(leftValueT2) && !rightValueT1.equals(rightValueT2)) {
            return false;
        }

        if (leftValueT1.equals(rightValueT2) && !rightValueT1.equals(leftValueT2)) {
            return false;
        }

        if (!(leftValueT1.equals(-1) && leftValueT2.equals(-1) && rightValueT1.equals(-1) && rightValueT2.equals(-1))) {
            if (leftValueT1.equals(leftValueT2)) {
                return equalTree(tree1.getLeftTree(), tree2.getLeftTree()) && equalTree(tree1.getRightTree(), tree2.getRightTree());
            }

            if (leftValueT1.equals(rightValueT2)) {
                return equalTree(tree1.getLeftTree(), tree2.getRightTree()) && equalTree(tree1.getRightTree(), tree2.getLeftTree());
            }
        }

        return true;
    }

    private Tree createTree1() {
        Tree tree = new Tree(1);

        Tree leftTree1 = new Tree(2);
        Tree rightTree1 = new Tree(3);

        tree.setLeftTree(leftTree1);
        tree.setRightTree(rightTree1);

        Tree leftTree1_1 = new Tree(4);
        Tree rightTree1_2 = new Tree(5);

        leftTree1.setLeftTree(leftTree1_1);
        leftTree1.setRightTree(rightTree1_2);

        Tree leftTree2_1 = new Tree(6);
        Tree rightTree2_2 = new Tree(7);

        rightTree1.setLeftTree(leftTree2_1);
        rightTree1.setRightTree(rightTree2_2);

        return tree;
    }

    private Tree createTree2() {
        Tree tree = new Tree(1);

        Tree leftTree1 = new Tree(3);
        Tree rightTree1 = new Tree(2);

        tree.setLeftTree(leftTree1);
        tree.setRightTree(rightTree1);

        Tree leftTree1_1 = new Tree(7);
        Tree rightTree1_2 = new Tree(6);

        leftTree1.setLeftTree(leftTree1_1);
        leftTree1.setRightTree(rightTree1_2);

        Tree leftTree2_1 = new Tree(4);
        Tree rightTree2_2 = new Tree(5);

        rightTree1.setLeftTree(leftTree2_1);
        rightTree1.setRightTree(rightTree2_2);

        return tree;
    }
}
