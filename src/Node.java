public class Node {
    Task task;
    Node prev;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.prev = null;
        this.next = null;
    }

    public Task getTask() {
        return task;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }
}