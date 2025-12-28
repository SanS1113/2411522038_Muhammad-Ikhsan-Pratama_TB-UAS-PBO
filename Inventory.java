public class Inventory {
    protected String id;
    protected String nama;

    public Inventory(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
}
