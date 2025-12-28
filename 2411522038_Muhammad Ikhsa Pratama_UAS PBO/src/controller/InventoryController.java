package controller;

import interfaces.InventoryActions;
import model.BarangOlehOleh;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InventoryController implements InventoryActions {

    private Connection conn;

    public InventoryController(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void tambahBarang(Scanner sc) {
        try {
            System.out.print("ID Barang: ");
            String id = sc.nextLine();
            System.out.print("Nama Barang: ");
            String nama = sc.nextLine();
            System.out.print("Stok: ");
            int stok = sc.nextInt();

            String sql = "INSERT INTO barang VALUES (?, ?, ?, CURDATE())";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, nama);
            ps.setInt(3, stok);
            ps.executeUpdate();

            System.out.println("Barang berhasil ditambahkan.");

        } catch (Exception e) {
            System.out.println("Gagal menambah barang.");
        }
    }

    @Override
    public void lihatBarang() {
        try {
            ArrayList<BarangOlehOleh> list = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM barang");

            while (rs.next()) {
                list.add(new BarangOlehOleh(
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getInt("stok")
                ));
            }

            System.out.println("\n--- DAFTAR BARANG ---");
            for (BarangOlehOleh b : list) {
                System.out.println(
                        b.getId() + " | " + b.getNama() + " | Stok: " + b.getStok()
                );
            }

        } catch (Exception e) {
            System.out.println("Gagal menampilkan data.");
        }
    }

    @Override
    public void updateBarang(Scanner sc) {
        try {
            System.out.print("ID Barang: ");
            String id = sc.nextLine();
            System.out.print("Stok Baru: ");
            int stok = sc.nextInt();

            String sql = "UPDATE barang SET stok=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stok);
            ps.setString(2, id);

            if (ps.executeUpdate() > 0)
                System.out.println("Stok berhasil diupdate.");
            else
                System.out.println("ID tidak ditemukan.");

        } catch (Exception e) {
            System.out.println("Gagal update stok.");
        }
    }

    @Override
    public void hapusBarang(Scanner sc) {
        try {
            System.out.print("ID Barang: ");
            String id = sc.nextLine();

            String sql = "DELETE FROM barang WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            if (ps.executeUpdate() > 0)
                System.out.println("Barang berhasil dihapus.");
            else
                System.out.println("ID tidak ditemukan.");

        } catch (Exception e) {
            System.out.println("Gagal hapus barang.");
        }
    }

    @Override
    public void hitungTotalStok() {
        try {
            ResultSet rs = conn.createStatement()
                    .executeQuery("SELECT SUM(stok) AS total FROM barang");

            if (rs.next()) {
                System.out.println("Total stok seluruh barang: " + rs.getInt("total"));
            }

        } catch (Exception e) {
            System.out.println("Gagal menghitung stok.");
        }
    }
}
