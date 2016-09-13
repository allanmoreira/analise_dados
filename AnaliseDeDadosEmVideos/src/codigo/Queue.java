package codigo;

/**
 * Created by allanmoreira on 12/09/16.
 */
public class Queue<E> {
    private int count;
    private Node<E> header;
    private Node<E> trailer;

    private class Node<T>{
        public T element;
        public Node <T> next;
        public Node <T> prev;

        public Node (T e){
            element = e;
            next = null;
            prev = null;
        }
    }

    public Queue(){
        header = new Node<>(null);
        trailer = new Node<>(null);
        count =0;
        header.next = trailer;
        trailer.prev = header;
    }

    
    public int size() {
        return count;
    }


    public boolean isEmpty() {
        return (count==0);
    }


    public E head() throws UnsupportedOperationException {
        if(header.next == null)
            throw new UnsupportedOperationException("A fila está vazia!");
        else
            return header.next.element;
    }


    public void enqueue(E element) {
        Node <E> n= new Node<>(element);

        trailer.prev.next = n;
        trailer.prev = n;
        n.next = trailer;
        n.prev = trailer.prev;
        count++;
    }

    
    public E dequeue() throws UnsupportedOperationException {
        if(count == 0)
            throw new UnsupportedOperationException("A fila estpa vazia!"); //To change body of generated methods, choose Tools | Templates.

        Node<E> n = new Node(null);
        n = header.next;

        header.next = header.next.next;
        count--;

        return n.element;
    }


    public void clear() {
        if(count == 0)
            throw new UnsupportedOperationException("A fila estpa vazia!"); //To change body of generated methods, choose Tools | Templates.
        else
            header.next = trailer;
        trailer.prev = header;
        count=0;
    }
}
