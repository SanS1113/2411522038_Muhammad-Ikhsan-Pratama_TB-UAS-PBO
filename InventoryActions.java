
import java.util.Scanner;

public interface InventoryActions {
    void tambahBarang(Scanner sc);
    void lihatBarang();
    void updateBarang(Scanner sc);
    void hapusBarang(Scanner sc);
    void hitungTotalStok();
}
