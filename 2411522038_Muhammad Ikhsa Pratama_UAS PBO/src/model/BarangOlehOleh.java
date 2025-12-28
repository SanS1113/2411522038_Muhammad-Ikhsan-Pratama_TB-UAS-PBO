package model;

public class BarangOlehOleh extends Inventory {
    private int stok;

    public BarangOlehOleh(String id, String nama, int stok) {
        super(id, nama);
        this.stok = stok;
    }

    public int getStok() {
        return stok;
    }
}
