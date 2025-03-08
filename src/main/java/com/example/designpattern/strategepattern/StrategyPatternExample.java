package com.example.designpattern.strategepattern;

// Giao diện Strategy cho việc tính thuế
interface TaxStrategy {
    double calculateTax(double price);
}

// Triển khai thuế tiêu thụ
class ConsumptionTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.05; // 5% thuế tiêu thụ
    }
}

// Triển khai thuế VAT
class VATTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.10; // 10% thuế VAT
    }
}

// Triển khai thuế xa xỉ phẩm
class LuxuryTax implements TaxStrategy {
    @Override
    public double calculateTax(double price) {
        return price * 0.20; // 20% thuế xa xỉ phẩm
    }
}

// Lớp Product đại diện cho sản phẩm
class Product {
    private String name;
    private double price;
    private TaxStrategy taxStrategy; // Strategy tính thuế

    public Product(String name, double price, TaxStrategy taxStrategy) {
        this.name = name;
        this.price = price;
        this.taxStrategy = taxStrategy;
    }

    public double calculateFinalPrice() {
        return price + taxStrategy.calculateTax(price);
    }

    public void displayPrice() {
        System.out.println(name + " - Giá gốc: " + price + " - Giá sau thuế: " + calculateFinalPrice());
    }
}

// Chương trình mô phỏng tính toán thuế cho các sản phẩm
public class StrategyPatternExample {
    public static void main(String[] args) {
        Product product1 = new Product("Bánh kẹo", 100, new ConsumptionTax());
        Product product2 = new Product("Điện thoại", 1000, new VATTax());
        Product product3 = new Product("Xe thể thao", 50000, new LuxuryTax());

        product1.displayPrice();
        product2.displayPrice();
        product3.displayPrice();
    }
}
