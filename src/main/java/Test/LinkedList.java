package Test;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2016/11/30
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class LinkedList<E> {

    /**
     * Head of linked list.
     * Invariant: head.item == null
     */
    private transient Node<E> head;

    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<E> last;

    public LinkedList(){
        last = head = new Node<E>(null);
    }

    static class Node<E>{
        E item;
        Node<E> next;
        public Node(E item){
            System.out.println("new Node");
            this.item = item;
        }
    }

    public void enQueue(Node<E> e){
        last = last.next = e;
    }
    public E deQueue(){
        Node<E> h = head;
        Node<E> first = head.next;
        h.next = h;
        head = first;
        E result = first.item;
        first.item = null;
        return result;
    }
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<String>();
        list.enQueue(new Node<String>("1111"));
        list.enQueue(new Node<String>("2222"));
        list.enQueue(new Node<String>("3333"));
        list.enQueue(new Node<String>("4444"));
        list.enQueue(new Node<String>("5555"));
        System.out.println(list.deQueue());
        System.out.println(list.deQueue());
        System.out.println(list.deQueue());
        System.out.println(list.deQueue());
        System.out.println(list.deQueue());

    }
}
