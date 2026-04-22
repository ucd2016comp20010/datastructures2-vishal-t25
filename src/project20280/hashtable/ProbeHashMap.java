package project20280.hashtable;

import project20280.interfaces.Entry;

import java.util.ArrayList;

public class ProbeHashMap<K, V> extends AbstractHashMap<K, V> {
    private MapEntry<K, V>[] table;
    private final MapEntry<K, V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashMap() {
        super();
    }

    /**
     * Creates a hash table with given capacity and prime factor 109345121.
     */
    public ProbeHashMap(int cap) {
        super(cap);
    }

    /**
     * Creates a hash table with the given capacity and prime factor.
     */
    public ProbeHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    protected void createTable() {
        table = new MapEntry[capacity];
    }

    /**
     * Find slot containing key k starting at index h. Returns slot index if
     * found, otherwise -1.
     */
    int findSlot(int h, K k) {
        int idx = h;
        while (table[idx] != null) {
            MapEntry<K, V> element = table[idx];
            if (element != DEFUNCT) {
                K elementKey = element.getKey();
                if (elementKey == null) {
                    if (k == null) return idx;
                } else {
                    if (elementKey.equals(k)) return idx;
                }
            }
            idx = (idx + 1) % capacity;
            if (idx == h) break; // full cycle
        }
        return -1;
    }

    @Override
    protected V bucketGet(int h, K k) {
        int slot = findSlot(h, k);
        if (slot == -1) return null;
        return table[slot].getValue();
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        int slot = findSlot(h, k);
        if (slot != -1) {
            V old = table[slot].getValue();
            table[slot].setValue(v);
            return old;
        }
        // locate first available slot (null or DEFUNCT)
        int availableSlot = -1;
        int idx = h;
        do {
            if (table[idx] == null) {
                if (availableSlot == -1) availableSlot = idx;
                break; // null ends probe sequence
            } else if (table[idx] == DEFUNCT) {
                if (availableSlot == -1) availableSlot = idx;
            }
            idx = (idx + 1) % capacity;
        } while (idx != h);

        if (availableSlot == -1) return null; // table full (should be avoided by resize)
        table[availableSlot] = new MapEntry<>(k, v);
        return null; // null signals a new entry was added
    }

    @Override
    protected V bucketRemove(int h, K k) {
        int slot = findSlot(h, k);
        if (slot == -1) return null;
        V old = table[slot].getValue();
        table[slot] = DEFUNCT;
        n--; // update count
        return old;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (MapEntry<K, V> e : table) {
            if (e != null && e != DEFUNCT) buffer.add(e);
        }
        return buffer;
    }
}
