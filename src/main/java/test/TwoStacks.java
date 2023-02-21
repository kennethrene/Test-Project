package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class TwoStacks {
    static class MyQueue<T> {
        Queue<T> queue = new LinkedList<>();

        public void enqueue(T nextInt) {
            queue.add(nextInt);
            System.out.println(queue);
        }

        public void dequeue() {
            queue.poll();
            System.out.println(queue);
        }

        public T peek() {
            return queue.peek();
        }
    }
    
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
