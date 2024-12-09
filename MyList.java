import java.util.Collection;

interface MyList<E> extends Collection<E> {
    public void add(int index, E e);
    public E get(int index);
    public int indexOf(Object e);
    public int lastIndexOf(E e);
    public E remove(int index);
    public E set(int index, E e);

    @Override
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean remove(Object e) {
        int index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public default Object[] toArray() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}