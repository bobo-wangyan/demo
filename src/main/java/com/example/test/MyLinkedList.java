package com.example.test;

public class MyLinkedList<T> {


    private  Node<T> root = new Node<>();

    public void addLast(T e){
         Node current = root;
         while (true){
             if(null == current.next){
                 current.next = new Node(e,null);
                 break;
             }
             current = current.next;
         }
    }

    public void getAll(){
        Node current = root.next;
        while (true){
            if(null != current){
                System.out.println(current.elelemt);
                current = current.next;

            }else{
                break;
            }
        }
    }

    public static void main(String[] args) {
         MyLinkedList<String> s = new MyLinkedList<>();
         s.addLast("1");
        s.addLast("2");
        s.addLast("3");
        s.addLast("4");
        s.getAll();

    }


    class Node<T> {
        private T elelemt;
        private Node<T> next;

        public Node() {
        }
        public Node(T elelemt, Node<T> next) {
            this.elelemt = elelemt;
            this.next = next;
        }

    }

}
