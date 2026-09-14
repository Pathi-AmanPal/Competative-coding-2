# LeetCode 232 - Implement Queue using Stacks

## 1. Intuition
A queue operates on a First-In-First-Out (FIFO) principle, whereas a stack operates on a Last-In-First-Out (LIFO) principle. By using two stacks (`input` and `output`), we can reverse the order of elements twice, restoring FIFO order. New elements are always pushed into `input`. When popping or peeking, if `output` is empty, we transfer all elements from `input` to `output`, flipping their order so the oldest element is at the top of `output`.

## 2. Approach
1. Maintain two stacks: `input` (for enqueue operations) and `output` (for dequeue/peek operations).
2. `push(int x)`: Push `x` directly onto `input` stack.
3. `peek()`:
   - If `output` is empty, pop all elements from `input` stack one by one and push them onto `output` stack.
   - Return `output.peek()`.
4. `pop()`:
   - Call `peek()` to ensure `output` contains the oldest elements.
   - Return `output.pop()`.
5. `empty()`: Return `true` if both `input` and `output` stacks are empty.

### Complexity
- **Time Complexity:**
  - `push(x)`: $O(1)$
  - `pop()`: Amortized $O(1)$ — Each element is pushed and popped between stacks at most twice across all operations.
  - `peek()`: Amortized $O(1)$
  - `empty()`: $O(1)$
- **Space Complexity:** $O(n)$ — Total elements stored across the two stacks.

## 3. Code
```java
import java.util.Stack;

class MyQueue {
    private Stack<Integer> input;
    private Stack<Integer> output;

    public MyQueue() {
        input = new Stack<>();
        output = new Stack<>();
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        peek();
        return output.pop();
    }
    
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}
```
