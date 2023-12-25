package io.github.labcentral.custom;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

public class LimitedOrderedSet<E> implements Iterable<E> {
    private final LinkedHashSet<E> set = new LinkedHashSet<>();
    private final int maxSize;


    public LimitedOrderedSet(int maxSize) {
        this.maxSize = maxSize;
    }

    public boolean contain(E element) {
        return set.contains(element);
    }

    public void setElementFirst(E element) {
        set.remove(element);
        set.add(element);
    }

    public E addAndReturnRemoveLast(E element) {
        E last = null;
        if (set.contains(element)) {
            // 如果元素已存在，移除它以便后续重新添加到第1个位置
            set.remove(element);
        } else if (set.size() >= maxSize) {
            // 如果已达到最大容量，移除最后一个元素
            last = set.iterator().next();
            set.remove(last);
        }
        // 将元素添加到第1个位置
        set.add(element);
        return last;
    }

    public LinkedHashSet<E> getElements() {
        return set;
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        set.forEach(action);
    }
}
