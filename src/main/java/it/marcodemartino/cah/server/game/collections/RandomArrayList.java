package it.marcodemartino.cah.server.game.collections;

import java.util.ArrayList;

public class RandomArrayList<E> extends ArrayList<E> {

    @Override
    public E remove(int index) {
        E toBeRemoved = get(index);
        swapToLast(index, toBeRemoved);
        return super.remove(size() - 1);
    }

    private void swapToLast(int index, E toBeRemoved) {
        E lastElement = get(size() - 1);
        set(index, lastElement);
        set(size() - 1, toBeRemoved);
    }

    public E removeRandom() {
        return remove((int) (Math.random() * size()));
    }
}
