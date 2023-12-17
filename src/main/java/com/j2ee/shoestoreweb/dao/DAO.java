package com.j2ee.shoestoreweb.dao;

import com.j2ee.shoestoreweb.context.DBContext;
import com.j2ee.shoestoreweb.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<SoLuongDaBan> getTop10SanPhamBanChay() {
        List<SoLuongDaBan> list = new ArrayList<>();
        String query = "SELECT * FROM SoLuongDaBan ORDER BY soLuongDaBan DESC LIMIT 10";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SoLuongDaBan(rs.getInt(1), rs.getInt(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account getAcc(String id) {
        List<Account> list = getAllAccount();
        for (Account value : list) {
            if (value.getId() == Integer.parseInt(id))
                return value;
        }
        return null;

    }

    public List<Invoice> getAllInvoice() {
        List<Invoice> list = new ArrayList<>();
        String query = "SELECT * FROM Invoice";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Invoice> searchInvoice(String name) {
        List<Invoice> list = getAllInvoice();
        List<Invoice> result = new ArrayList<>();
        try {
            result = SearchIdInvoice(name, list);
            if (result.size() == 0) {
                result = SearchMoneyInvoice(name, list);
            }
        } catch (Exception ex) {
            result = SearchNameCustomerInvoice(name, list);
        }
        return result;
    }

    public List<Account> searchAccount(String name) {
        List<Account> list = getAllAccount();
        List<Account> result = new ArrayList<>();
        result = searchName(name, list);
        if (result.size() == 0) {
            result = searchEmail(name, list);
        }
        return result;
    }

    public List<Account> searchName(String name, List<Account> list) {
        List<Account> result = new ArrayList<>();


        while (name.length() > 1 && result.size() == 0) {

            for (Account value : list) {
                String n = value.getUser();
                n = n.substring(0, name.length());
                if (n.equals(name)) {
                    result.add(value);
                }
            }
            if (result.size() == 0) {
                name = name.substring(0, name.length() - 1);
            }
        }
        return result;
    }

    public List<Account> searchEmail(String name, List<Account> list) {
        List<Account> result = new ArrayList<>();
        for (Account value : list) {
            if (value.getEmail().equals(name)) {
                result.add(value);
                break;
            }
        }
        return result;
    }

    public List<Invoice> SearchIdInvoice(String name, List<Invoice> list) {
        List<Invoice> result = new ArrayList<>();
        for (Invoice value : list) {
            if (value.getMaHD() == Integer.parseInt(name)) {
                result.add(value);
                break;
            }

        }
        return result;

    }

    public List<Invoice> SearchNameCustomerInvoice(String name, List<Invoice> list) {
        List<Invoice> result = new ArrayList<>();
        List<Account> listCu = getAllAccount();
        int idCu = -1;
        for (Account value : listCu) {
            if (value.getUser().contains(name)) {
                idCu = value.getId();
                break;
            }
        }

        if (idCu != -1) {
            for (Invoice value : list) {
                if (value.getAccountID() == idCu) {
                    result.add(value);

                }

            }
        }
        return result;

    }

    public List<Invoice> SearchMoneyInvoice(String name, List<Invoice> list) {
        List<Invoice> result = new ArrayList<>();
        for (Invoice value : list) {
            if (value.getTongGia() == Integer.parseInt(name)) {
                result.add(value);
            }

        }
        return result;

    }

    public int countAllProductBySellID(int sell_ID) {
        String query = "SELECT COUNT(*) FROM Product WHERE sell_ID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, sell_ID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getSellIDByProductID(int productID) {
        String query = "SELECT sell_ID FROM Product WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double totalMoneyDay(int day) {
        int year = 2021;
        int month = 11;
        String arr[] = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
        int arrDay[] = {0, 0, 0, 0, 0, 0, 0};

        LocalDate today = java.time.LocalDate.now();
        String a = today.getDayOfWeek().toString();

        year = today.getYear();
        month = today.getMonthValue();
        int index = 1;
        int dayOfMonth = today.getDayOfMonth();

        switch (a) {
            case "SUNDAY":

                index = 1;
                break;
            case "MONDAY":

                index = 2;
                break;

            case "TUESDAY":
                index = 3;
                break;
            case "WEDNESDAY":

                index = 4;
                break;

            case "THURSDAY":

                index = 5;
                break;
            case "FRIDAY":

                index = 6;
                break;

            case "SATURDAY":

                index = 7;
                break;
        }
        for (int i = 0; i < arrDay.length; i++) {
            if (i + 1 < index) {
                int dayValue = dayOfMonth - (index - (i + 1));
                arrDay[i] = dayValue;
            }
            if (i + 1 == index)
                arrDay[i] = dayOfMonth;
            if (i + 1 > index) {
                int dayValue = dayOfMonth + ((i + 1) - index);
                arrDay[i] = dayValue;
            }
        }
        List<Invoice> list = getAllInvoice();
        double sum = 0;
        for (Invoice value : list) {

            String s = value.getNgayXuat().toString();
            String temp[] = s.split("-");
            if (Integer.parseInt(temp[0]) == year && Integer.parseInt(temp[1]) == month
                    && Integer.parseInt(temp[2]) == arrDay[day - 1]) {
                sum += value.getTongGia();
            }
        }
        return sum;


//        String query = "SELECT SUM(tongGia) FROM Invoice " +
//                "WHERE DAYOFWEEK(ngayXuat) = ? " +
//                "GROUP BY ngayXuat";
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setInt(1, day);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return rs.getDouble(1);
//            }
//        } catch (Exception e) {
//        }
//        return 0;
    }

    public double totalMoneyMonth(int month) {
        int year = java.time.LocalDate.now().getYear();
        double sum = 0;
        List<Invoice> list = getAllInvoice();

        for (Invoice value : list) {

            String s = value.getNgayXuat().toString();
            String temp[] = s.split("-");
            if (Integer.parseInt(temp[0]) == year && Integer.parseInt(temp[1]) == month) {
                sum += value.getTongGia();
            }
        }

        return sum;
    }

    public List<Invoice> InvoiceOfMonth(int month) {
        int year = java.time.LocalDate.now().getYear();
        double sum = 0;
        List<Invoice> list = getAllInvoice();
        List<Invoice> result = new ArrayList<>();
        for (Invoice value : list) {

            String s = value.getNgayXuat().toString();
            String temp[] = s.split("-");
            if (Integer.parseInt(temp[0]) == year && Integer.parseInt(temp[1]) == month) {
                result.add(value);
            }
        }

        return result;
    }

    public int countAllProduct() {
        String query = "SELECT COUNT(*) FROM Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public double sumAllInvoice() {
        String query = "SELECT SUM(tongGia) FROM Invoice";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int countAllReview() {
        String query = "SELECT COUNT(*) FROM Review";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getCateIDByProductID(String id) {
        String query = "SELECT cateID FROM Product " +
                "WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String query = "SELECT * FROM Account";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Supplier> getAllSupplier() {
        List<Supplier> list = new ArrayList<>();
        String query = "SELECT * FROM Supplier";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Supplier(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<TongChiTieuBanHang> getTop5KhachHang() {
        List<TongChiTieuBanHang> list = new ArrayList<>();
        String query = "SELECT * FROM TongChiTieuBanHang ORDER BY TongChiTieu DESC LIMIT 5";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TongChiTieuBanHang(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<TongChiTieuBanHang> getTop5NhanVien() {
        List<TongChiTieuBanHang> list = new ArrayList<>();
        String query = "SELECT * FROM TongChiTieuBanHang ORDER BY TongBanHang DESC LIMIT 5";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TongChiTieuBanHang(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getDouble(3)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getTop3() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product LIMIT 3";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getNext3Product(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY id LIMIT 3 OFFSET ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getNext4NikeProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE cateID = 2 ORDER BY id DESC LIMIT 4 OFFSET ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getNext4AdidasProduct(int amount) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE cateID = 1 " +
                "ORDER BY id DESC " +
                "LIMIT ?, 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE cateID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getProductBySellIDAndIndex(int id, int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE sell_ID = ? " +
                "ORDER BY id " +
                "LIMIT ?, 5";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setInt(2, (indexPage - 1) * 5);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> SearchProduct(String name) {
        List<Product> list = getAllProduct();
        List<Product> result = new ArrayList<>();

        try {
            result = SearchProductId(name, list);
            if (result.size() == 0) {
                result = SearchProductPrice(name, list);
            }
        } catch (Exception ex) {
            result = SearchProductName(name, list);
        }

        return result;

    }

    public List<Supplier> SearchSupplier(String name) {
        List<Supplier> list = getAllSupplier();
        List<Supplier> result = new ArrayList<>();
        try {
            result = SearchSupplierId(name, list);
        } catch (Exception ex) {
            result = SearchSupplierName(name, list);

        }
        return result;
    }

    public List<Supplier> SearchSupplierId(String name, List<Supplier> list) {
        List<Supplier> result = new ArrayList<>();
        for (Supplier value : list) {

            if (value.getIdSupplier() == Integer.parseInt(name)) {
                result.add(value);
                break;
            }
        }
        return result;

    }

    public List<Supplier> SearchSupplierName(String name, List<Supplier> list) {
        List<Supplier> result = new ArrayList<>();
        while (name.length() > 1 && result.size() == 0) {
            for (Supplier value : list) {
                String n = value.getNameSupplier();
                n = n.substring(0, name.length());
                if (n.equals(name)) {
                    result.add(value);

                }
            }
            if (result.size() == 0) {

                name = name.substring(0, name.length() - 1);
            }


        }
        return result;

    }


    public List<Product> SearchProductId(String name, List<Product> list) {
        List<Product> result = new ArrayList<>();

        for (Product value : list) {

            if (value.getId() == Integer.parseInt(name)) {
                result.add(value);
                break;
            }
        }

        return result;
    }

    public List<Product> SearchProductName(String name, List<Product> list) {
        List<Product> result = new ArrayList<>();

        while (name.length() > 1 && result.size() == 0) {
            for (Product value : list) {
                String n = value.getName();
                n = n.substring(0, name.length());
                if (n.compareToIgnoreCase(name) == 0) {
                    result.add(value);
                }
            }
            if (result.size() == 0) {
                name = name.substring(0, name.length() - 1);
            }

        }

        return result;
    }

    public List<Product> SearchProductPrice(String name, List<Product> list) {
        List<Product> result = new ArrayList<>();


        for (Product value : list) {

            double price = Double.parseDouble(name);

            if (value.getPrice() == price || ((value.getPrice() - 1) < price && value.getPrice() + 1 > price)) {
                result.add(value);

            }
        }


        return result;
    }

    public List<Product> getProductByIndex(int indexPage) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "ORDER BY id " +
                "LIMIT ?, 9";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, (indexPage - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE name LIKE ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Invoice> searchByNgayXuat(String ngayXuat) {
        List<Invoice> list = new ArrayList<>();
        String query = "SELECT * FROM Invoice WHERE ngayXuat = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "13");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Invoice(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchPriceUnder100() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE price < 100";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchPrice100To200() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE price >= 100 AND price <= 200";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchColorWhite() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE color = 'White'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchColorGray() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE color = 'Gray'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchColorBlack() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product WHERE color = 'Black'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
                        rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10),
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchColorYellow() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where color = 'Yellow'";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchByPriceMinToMax(String priceMin, String priceMax) {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where price >= ? and price <= ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, priceMin);
            ps.setString(2, priceMax);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> searchPriceAbove200() {
        List<Product> list = new ArrayList<>();
        String query = "select * from Product\n"
                + "where price > 200";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> getRelatedProduct(int cateIDProductDetail) {
        List<Product> list = new ArrayList<>();
        String query = "select * from product\n"
                + "where cateID = ?\n"
                + "order by id desc\n"
                + "limit 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cateIDProductDetail);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Review> getAllReviewByProductID(String productId) {
        List<Review> list = new ArrayList<>();
        String query = "select * from Review\n"
                + "where productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Review(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDate(4)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductByID(String id) {
        String query = "select * from Product\n"
                + "where id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Cart> getCartByAccountID(int accountID) {
        List<Cart> list = new ArrayList<>();
        String query = "select * from Cart where accountID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Cart(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getString(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Cart checkCartExist(int accountID, int productID) {
        String query = "SELECT * FROM Cart WHERE accountID = ? AND productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Cart(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int checkAccountAdmin(int userID) {
        String query = "SELECT isAdmin FROM Account WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public TongChiTieuBanHang checkTongChiTieuBanHangExist(int userID) {
        String query = "SELECT * FROM TongChiTieuBanHang WHERE userID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new TongChiTieuBanHang(rs.getInt(1), rs.getDouble(2), rs.getDouble(3));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public SoLuongDaBan checkSoLuongDaBanExist(int productID) {
        String query = "SELECT * FROM SoLuongDaBan WHERE productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new SoLuongDaBan(rs.getInt(1), rs.getInt(2));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Category";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "SELECT * FROM Product ORDER BY id DESC LIMIT 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> get8Last() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY id DESC LIMIT 8";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getString(6), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> get4NikeLast() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE cateID = 2 " +
                "ORDER BY id DESC " +
                "LIMIT 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Product> get4AdidasLast() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product " +
                "WHERE cateID = 1 " +
                "ORDER BY id DESC " +
                "LIMIT 4";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Account login(String user, String pass) {
        String query = "SELECT * FROM Account " +
                "WHERE `user` = ? " +
                "AND `pass` = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account checkAccountExist(String user) {
        String query = "SELECT * FROM Account " +
                "WHERE `user` = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account checkAccountExistByUsernameAndEmail(String username, String email) {
        String query = "SELECT * FROM Account " +
                "WHERE `user` = ? AND `email` = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Review getNewReview(int accountID, int productID) {
        String query = "SELECT * FROM Review " +
                "WHERE accountID = ? AND productID = ? " +
                "ORDER BY maReview DESC " +
                "LIMIT 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Review(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void singup(String user, String pass, String email) {
        String query = "INSERT INTO Account (userName, password, col1, col2, email) " +
                "VALUES (?, ?, 0, 0, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteInvoiceByAccountId(String id) {
        String query = "DELETE FROM Invoice " +
                "WHERE accountID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteTongChiTieuBanHangByUserID(String id) {
        String query = "DELETE FROM TongChiTieuBanHang " +
                "WHERE userID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProduct(String pid) {
        String query = "DELETE FROM Product " +
                "WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteProductBySellID(String id) {
        String query = "DELETE FROM Product " +
                "WHERE sell_ID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCartByAccountID(int accountID) {
        String query = "DELETE FROM Cart " +
                "WHERE accountID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCartByProductID(String productID) {
        String query = "DELETE FROM Cart " +
                "WHERE productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteSoLuongDaBanByProductID(String productID) {
        String query = "DELETE FROM SoLuongDaBan " +
                "WHERE productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteReviewByProductID(String productID) {
        String query = "DELETE FROM Review " +
                "WHERE productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteReviewByAccountID(String id) {
        String query = "DELETE FROM Review " +
                "WHERE accountID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteAccount(String id) {
        String query = "DELETE FROM Account " +
                "WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteSupplier(String idSupplier) {
        String query = "DELETE FROM Supplier " +
                "WHERE idSupplier = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idSupplier);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void deleteCart(int productID) {
        String query = "DELETE FROM Cart WHERE productID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertProduct(String name, String image, String price,
                              String title, String description, String category, int sid, String model, String color, String delivery, String image2, String image3, String image4) {
        String query = "INSERT INTO Product (name, image, price, title, description, cateID, sell_ID, model, color, delivery, image2, image3, image4) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price);
            ps.setString(4, title);
            ps.setString(5, description);
            ps.setString(6, category);
            ps.setInt(7, sid);
            ps.setString(8, model);
            ps.setString(9, color);
            ps.setString(10, delivery);
            ps.setString(11, image2);
            ps.setString(12, image3);
            ps.setString(13, image4);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertAccount(String user, String pass, String isSell,
                              String isAdmin, String email) {
        String query = "INSERT INTO Account (user, pass, isSell, isAdmin, email) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, isSell);
            ps.setString(4, isAdmin);
            ps.setString(5, email);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertTongChiTieuBanHang(int userID, double tongChiTieu, double tongBanHang) {
        String query = "INSERT INTO TongChiTieuBanHang (userID, TongChiTieu, TongBanHang) " +
                "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setDouble(2, tongChiTieu);
            ps.setDouble(3, tongBanHang);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertSoLuongDaBan(int productID, int soLuongDaBan) {
        String query = "INSERT INTO SoLuongDaBan (productID, soLuongDaBan) " +
                "VALUES (?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, soLuongDaBan);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertSupplier(String nameSupplier, String phoneSupplier, String emailSupplier, String addressSupplier, String cateID) {
        String query = "INSERT INTO Supplier (nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, nameSupplier);
            ps.setString(2, phoneSupplier);
            ps.setString(3, emailSupplier);
            ps.setString(4, addressSupplier);
            ps.setString(5, cateID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void AddSupplier(Supplier su) {
        String query = "INSERT INTO Supplier (nameSupplier, phoneSupplier, emailSupplier, addressSupplier, cateID) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, su.getNameSupplier());
            ps.setString(2, su.getPhoneSupplier());
            ps.setString(3, su.getEmailSupplier());
            ps.setString(4, su.getAddressSupplier());
            ps.setString(5, su.getCateID() + "");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    private static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    public void insertReview(int accountID, int productID, String contentReview) {
        String query = "INSERT INTO Review (accountID, productID, contentReview, dateReview) " +
                "VALUES (?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setString(3, contentReview);
            ps.setDate(4, getCurrentDate());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertInvoice(int accountID, double tongGia) {
        String query = "INSERT INTO Invoice (accountID, tongGia, ngayXuat) " +
                "VALUES (?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setDouble(2, tongGia);
            ps.setDate(3, getCurrentDate());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void insertCart(int accountID, int productID, int amount, String size) {
        String query = "INSERT INTO Cart (accountID, productID, amount, size) "
                + "VALUES (?, ?, ?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setInt(2, productID);
            ps.setInt(3, amount);
            ps.setString(4, size);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProduct(String pname, String pimage, String pprice, String ptitle, String pdescription, String pcategory,
                            String pmodel, String pcolor,
                            String pdelivery, String pimage2, String pimage3, String pimage4, String pid) {
        String query = "UPDATE Product "
                + "SET name = ?, "
                + "image = ?, "
                + "price = ?, "
                + "title = ?, "
                + "description = ?, "
                + "cateID = ?, "
                + "model = ?, "
                + "color = ?, "
                + "delivery = ?, "
                + "image2 = ?, "
                + "image3 = ?, "
                + "image4 = ? "
                + "WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, pname);
            ps.setString(2, pimage);
            ps.setString(3, pprice);
            ps.setString(4, ptitle);
            ps.setString(5, pdescription);
            ps.setString(6, pcategory);
            ps.setString(7, pmodel);
            ps.setString(8, pcolor);
            ps.setString(9, pdelivery);
            ps.setString(10, pimage2);
            ps.setString(11, pimage3);
            ps.setString(12, pimage4);
            ps.setString(13, pid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editProfile(String username, String password, String email, int uID) {
        String query = "UPDATE Account "
                + "SET user = ?, "
                + "pass = ?, "
                + "email = ? "
                + "WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, uID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void UpdateAccount(Account acc) {
        String query = "UPDATE Account "
                + "SET user = ?, "
                + "pass = ?, "
                + "isSell = ?, "
                + "isAdmin = ?, "

                + "email = ? "
                + "WHERE uID = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, acc.getUser());
            ps.setString(2, acc.getPass());
            ps.setString(3, acc.getIsSell() + "");
            ps.setString(4, acc.getIsAdmin() + "");
            ps.setString(5, acc.getEmail());
            ps.setInt(6, acc.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editTongChiTieu(int accountID, double totalMoneyVAT) {
        String query = "CALL proc_CapNhatTongChiTieu(?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountID);
            ps.setDouble(2, totalMoneyVAT);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editSoLuongDaBan(int productID, int soLuongBanThem) {
        String query = "CALL proc_CapNhatSoLuongDaBan(?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productID);
            ps.setInt(2, soLuongBanThem);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editTongBanHang(int sell_ID, double tongTienBanHangThem) {
        String query = "CALL proc_CapNhatTongBanHang(?, ?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, sell_ID);
            ps.setDouble(2, tongTienBanHangThem);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editAmountAndSizeCart(int accountID, int productID, int amount, String size) {
        String query = "UPDATE Cart SET `amount`=?, `size`=? WHERE `accountID`=? AND `productID`=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            ps.setString(2, size);
            ps.setInt(3, accountID);
            ps.setInt(4, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void editAmountCart(int accountID, int productID, int amount) {
        String query = "UPDATE Cart SET `amount`=? WHERE `accountID`=? AND `productID`=?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, amount);
            ps.setInt(2, accountID);
            ps.setInt(3, productID);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}