import java.sql.Connection;
import java.util.Scanner;

import controller.InventoryController;
import database.DatabaseConnection;

public class App {
    public static void main(String[] args) {

        try {
            Connection conn = DatabaseConnection.connect();
            InventoryController controller = new InventoryController(conn);
            Scanner sc = new Scanner(System.in);

            boolean jalan = true;

            while (jalan) {
                System.out.println("\n=== SISTEM INVENTORY TOKO TEMAN ===");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Lihat Barang");
                System.out.println("3. Update Stok");
                System.out.println("4. Hapus Barang");
                System.out.println("5. Hitung Total Stok");
                System.out.println("6. Keluar");
                System.out.print("Pilih menu: ");

                int menu = sc.nextInt();
                sc.nextLine();

                switch (menu) {
                    case 1 -> controller.tambahBarang(sc);
                    case 2 -> controller.lihatBarang();
                    case 3 -> controller.updateBarang(sc);
                    case 4 -> controller.hapusBarang(sc);
                    case 5 -> controller.hitungTotalStok();
                    case 6 -> {
                        jalan = false;
                        System.out.println("Aplikasi ditutup.");
                    }
                    default -> System.out.println("Menu tidak tersedia.");
                }
            }
            sc.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Error aplikasi: " + e.getMessage());
        }
    }
}
