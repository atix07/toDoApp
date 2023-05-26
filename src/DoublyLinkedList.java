public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void removeTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getId() == taskId) {
                // If the current node is the head, the head pointer 
                // is updated to point to the next node in the list
                if (current.prev == null) {
                    head = current.next;
                } else {
                    // If the current node is not the head, the next 
                    // pointer of the previous node is updated to bypass the current node.
                    current.prev.next = current.next;
                }
                // If the current node is the tail, the tail pointer is updated 
                // to point to the previous node in the list
                if (current.next == null) {
                    tail = current.prev;
                } else {
                    // If the current node is not the tail, the prev pointer of 
                    // the next node is updated to skip the current node
                    current.next.prev = current.prev;
                }
                size--;
                break;
            }
            current = current.next;
        }
    }

    public void displayTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }
}