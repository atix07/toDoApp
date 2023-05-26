public class MinHeap {
    private Task[] heapArray;
    private int size;

    public MinHeap(int capacity) {
        heapArray = new Task[capacity];
        size = 0;
    }

    public void addTask(Task task) {
        if (size == heapArray.length) {
            System.out.println("Heap is full. Task cannot be added.");
            return;
        }
        heapArray[size] = task;
        int current = size;
        while (current > 0 && heapArray[current].getPriority() < heapArray[parentIndex(current)].getPriority()) {
            swap(current, parentIndex(current));
            current = parentIndex(current);
        }
        size++;
    }

    public Task removeHighestPriorityTask() {
        if (size == 0) {
            System.out.println("Heap is empty.");
            return null;
        }
        Task root = heapArray[0];
        heapArray[0] = heapArray[size - 1];
        size--;
        heapify(0);
        return root;
    }

    private void heapify(int index) {
        int smallest = index;
        int leftChild = leftChildIndex(index);
        int rightChild = rightChildIndex(index);

        if (leftChild < size && heapArray[leftChild].getPriority() < heapArray[smallest].getPriority()) {
            smallest = leftChild;
        }

        if (rightChild < size && heapArray[rightChild].getPriority() < heapArray[smallest].getPriority()) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    public void displayTasks() {
        for (int i = 0; i < size; i++) {
            System.out.println(heapArray[i]);
        }
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int rightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private void swap(int index1, int index2) {
        Task temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
    }

    public Task[] getHeapArray() {
        return heapArray;
    }

    public int getSize() {
        return size;
    }

    public Task removeTask(int taskId) {
        int taskIndex = -1;
        for (int i = 0; i < size; i++) {
            if (heapArray[i].getId() == taskId) {
                taskIndex = i;
                break;
            }
        }

        if (taskIndex == -1) {
            System.out.println("Task not found in the heap.");
            return null;
        }

        Task removedTask = heapArray[taskIndex];
        heapArray[taskIndex] = heapArray[size - 1];
        size--;
        heapify(taskIndex);
        System.out.println("Task removed from the heap.");
        return removedTask;
    }
}