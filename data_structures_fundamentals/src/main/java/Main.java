import linear_data_structures.impl.ArrayList;
import linear_data_structures.impl.DoublyLinkedList;
import linear_data_structures.impl.Queue;
import linear_data_structures.impl.Stack;
import linear_data_structures.interfaces.AbstractQueue;
import linear_data_structures.interfaces.AbstractStack;
import linear_data_structures.interfaces.LinkedList;
import linear_data_structures.interfaces.List;
import trees.impl.MaxHeap;
import trees.interfaces.Heap;

public class Main {
    public static void main(String[] args) {
        /**
         * ArrayList test as a user
         */
/*
        List<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.insertAt(1, 15);

        System.out.println(numbers.remove(0));
        System.out.println();
        numbers.insertAt(0, 25);

        for (Integer number : numbers) {
            System.out.println(number);
        }
*/

        /**
         * Stack test as a user
         */
/*
        AbstractStack<Integer> numbers = new Stack<>();
        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        numbers.push(40);
        numbers.push(50);

        System.out.println(numbers.peek());
        System.out.println(numbers.pop());
        System.out.println();

        for (Integer number : numbers) {
            System.out.println(number);
        }
*/

        /**
         * Queue test as a user
         */
/*
        AbstractQueue<String> laptopBrands = new Queue<>();
        laptopBrands.offer("Lenovo");
        laptopBrands.offer("Razer");
        laptopBrands.offer("Dell");
        laptopBrands.offer("Alien Ware");

        laptopBrands.poll();
        laptopBrands.poll();
        laptopBrands.poll();
        laptopBrands.poll();

        laptopBrands.offer("fdsfds");
        System.out.println(laptopBrands.size());

        for (String brand : laptopBrands) {
            System.out.println(brand);
        }
*/

//        LinkedList<Integer> numbers = new DoublyLinkedList<>();
//        numbers.addFirst(10);
//        numbers.addLast(20);
//        numbers.addLast(30);
//        numbers.addFirst(5);
//
//        numbers.remove(20);
//
//
//        for (Integer number : numbers) {
//            System.out.println(number);
//        }

        Heap<Integer> heap = new MaxHeap<>();
        heap.add(5);
        heap.add(4);
        heap.add(1);
        heap.add(3);
        heap.add(2);
        heap.add(6);
        heap.add(25);

        for (Integer number : heap) {
            System.out.println(number);
        }

    }
}
