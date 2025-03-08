package com.example.designpattern.fatorypattern;

// Giao diện chung cho các dịch vụ giao hàng
interface ShippingService {
    double calculateFee(double weight, double distance);
}

// Lớp cụ thể: Giao hàng tiêu chuẩn
class StandardShipping implements ShippingService {
    @Override
    public double calculateFee(double weight, double distance) {
        return weight * 5 + distance * 2; // Công thức tính phí
    }
}

// Lớp cụ thể: Giao hàng nhanh
class ExpressShipping implements ShippingService {
    @Override
    public double calculateFee(double weight, double distance) {
        return weight * 8 + distance * 3;
    }
}

// Lớp cụ thể: Giao hàng quốc tế
class InternationalShipping implements ShippingService {
    @Override
    public double calculateFee(double weight, double distance) {
        return weight * 15 + distance * 5;
    }
}

// Factory Method để tạo dịch vụ giao hàng
class ShippingFactory {
    public static ShippingService getShippingService(String type) {
        switch (type) {
            case "Standard":
                return new StandardShipping();
            case "Express":
                return new ExpressShipping();
            case "International":
                return new InternationalShipping();
            default:
                throw new IllegalArgumentException("Loại giao hàng không hợp lệ!");
        }
    }
}

// Chương trình kiểm tra Factory Method
public class FactoryMethodExample {
    public static void main(String[] args) {
        ShippingService standard = ShippingFactory.getShippingService("Standard");
        ShippingService express = ShippingFactory.getShippingService("Express");

        System.out.println("Phí giao hàng tiêu chuẩn: " + standard.calculateFee(10, 50) + " VND");
        System.out.println("Phí giao hàng nhanh: " + express.calculateFee(10, 50) + " VND");
    }
}

