public class List {
    Node head;

    public void append(Documento data){
        if(head == null){
           head = new Node(data);
           return;
        }
        Node current = head;
        while(current.next != null){
            current  = current.next;
        }
        current.next = new Node(data);
    }

    public void printList() {
        if(head == null){
            System.out.println("Lista vazia.");
            return;
        }
        Node current = head;
        while(current != null){
            System.out.println(current.data.titulo);
            current  = current.next;
        }
    }
}
