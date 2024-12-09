import java.util.*;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public TwoWayLinkedList() {}

    public TwoWayLinkedList(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = new Node<>(e);
            temp.next = current;
            temp.previous = current.previous;
            current.previous.next = temp;
            current.previous = temp;
            size++;
        }
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return current.element;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<E> temp = head;
        head = head.next;
        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }
        size--;
        return temp.element;
    }

    public E removeLast() {
        if (size == 0) {
            return null;
        }
        Node<E> temp = tail;
        tail = tail.previous;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        size--;
        return temp.element;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        return new ListIterator<>() {
            private Node<E> current = (index == 0) ? head : getNode(index);
            private Node<E> lastReturned = null;
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastReturned = current;
                E element = current.element;
                current = current.next;
                currentIndex++;
                return element;
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                current = (current == null) ? tail : current.previous;
                lastReturned = current;
                currentIndex--;
                return current.element;
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                if (lastReturned == head) {
                    removeFirst();
                } else if (lastReturned == tail) {
                    removeLast();
                } else {
                    lastReturned.previous.next = lastReturned.next;
                    lastReturned.next.previous = lastReturned.previous;
                    size--;
                }
                lastReturned = null;
            }

            @Override
            public void set(E e) {
                if (lastReturned == null) {
                    throw new IllegalStateException();
                }
                lastReturned.element = e;
            }

            @Override
            public void add(E e) {
                if (current == null) {
                    addLast(e);
                } else {
                    Node<E> newNode = new Node<>(e);
                    newNode.next = current;
                    newNode.previous = current.previous;
                    if (current.previous != null) {
                        current.previous.next = newNode;
                    } else {
                        head = newNode;
                    }
                    current.previous = newNode;
                    size++;
                }
                lastReturned = null;
                currentIndex++;
            }

            private Node<E> getNode(int index) {
                Node<E> current = head;
                for (int i = 0; i < index; i++) {
                    current = current.next;
                }
                return current;
            }
        };
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (Objects.equals(current.element, o)) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.element, o)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = tail;
        int index = size - 1;
        while (current != null) {
            if (Objects.equals(current.element, o)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }
}