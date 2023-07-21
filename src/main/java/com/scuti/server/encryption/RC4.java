package com.scuti.server.encryption;

public class RC4 {

    private static final int POOL_SIZE = 256;

    private int i = 0;
    private int j = 0;
    private final int[] table;

    public RC4() {
        this.table = new int[POOL_SIZE];
    }

    public RC4(byte[] key) {
        this.table = new int[POOL_SIZE];
        this.init(key);
    }

    public void init(byte[] key) {
        this.i = 0;
        this.j = 0;

        for (i = 0; i < POOL_SIZE; ++i) {
            this.table[i] = (byte) i;
        }

        for (i = 0; i < POOL_SIZE; ++i) {
            j = (j + table[i] + key[i % key.length]) & (POOL_SIZE - 1);
            swap(i, j);
        }

        this.i = 0;
        this.j = 0;
    }

    public void swap(int a, int b) {
        int k = this.table[a];
        this.table[a] = this.table[b];
        this.table[b] = k;
    }

    public byte next() {
        i = ++i & (POOL_SIZE - 1);
        j = (j + table[i]) & (POOL_SIZE - 1);
        swap(i, j);

        return (byte) table[(table[i] + table[j]) & (POOL_SIZE - 1)];
    }

    public byte[] decipher(byte[] array) {

        byte[] result = new byte[array.length];

        for (int i = 0; i < array.length; i++) {
            result[i] = (byte) (array[i] ^ this.next());
        }

        return result;
    }
}
