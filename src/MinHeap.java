public class MinHeap {
    private Task[] heapArray;
    private int size;

    public MinHeap(int capacity) {
        heapArray = new Task[capacity];
        size = 0;
    }

    public void addTask(Task task) {
        // This condition checks if the heap is already full, meaning it has reached its maximum capacity. 
        if (size == heapArray.length) {
            System.out.println("Heap is full. Task cannot be added.");
            return;
        }
        // If the heap is not full, the task is added to the heap at the position indicated by the size variable. 
        heapArray[size] = task;
        int current = size;

        // This loop ensures that the newly added task is placed in the correct position within the heap based on its priority. 
        // The loop continues as long as the current is not the root
        while (current > 0 && heapArray[current].getPriority() < heapArray[parentIndex(current)].getPriority()) {
            swap(current, parentIndex(current));
            current = parentIndex(current); 
        }
        size++;
    }

    private void heapify(int index) {
        int smallest = index;
        int leftChild = leftChildIndex(index);
        int rightChild = rightChildIndex(index);

        // If the left childs priority is less than the priority of the current smallest node 
        if (leftChild < size && heapArray[leftChild].getPriority() < heapArray[smallest].getPriority()) {
            smallest = leftChild;
        }

        // If the right childs priority is less than the priority of the current smallest node 
        if (rightChild < size && heapArray[rightChild].getPriority() < heapArray[smallest].getPriority()) {
            smallest = rightChild;
        }

        // If the smallest index is different from the original index
        // If it is, it means that either the left child or the right child has a smaller priority than the current node
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
        // The task at the taskIndex is replaced with the task at the last index of the heap.
        heapArray[taskIndex] = heapArray[size - 1];
        size--;
        // To adjusting the position of the task
        heapify(taskIndex);
        System.out.println("Task removed from the heap.");
        return removedTask;
    }
}