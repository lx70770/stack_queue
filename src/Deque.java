public class Deque<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public Deque(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.front = 0;
        this.tail = 0;
        this.size = 0;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    public void addFirst(E e) {
        if (size == getCapacity()) {
            resize(getCapacity() * 2);
        }
        front = front == 0 ? data.length - 1 : front - 1;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from a empty queue");
        }
        E res = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return res;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }
}
