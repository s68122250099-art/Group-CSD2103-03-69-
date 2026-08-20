package utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class MyStack<T> {
    private Deque<T> stack = new ArrayDeque<>();
    private long pushCount = 0;
    private long popCount = 0;

    public void push(T item) {
        stack.push(item);
        pushCount++;
    }

    public T pop() {
        if (isEmpty()) return null;
        popCount++;
        return stack.pop();
    }

    public T peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void clear() {
        stack.clear();
    }

    public long getPushCount() { return pushCount; }
    public long getPopCount() { return popCount; }
    public void resetOpCount() { pushCount = 0; popCount = 0; }
}