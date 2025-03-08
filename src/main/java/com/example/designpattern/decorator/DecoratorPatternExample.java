package com.example.designpattern.decorator;

// Giao diện Payment (Component)
interface Payment {
    void pay(double amount);
}

// Lớp cụ thể: Thanh toán bằng Thẻ tín dụng
class CreditCardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng thẻ tín dụng: " + amount + " VND");
    }
}

// Lớp cụ thể: Thanh toán bằng PayPal
class PayPalPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán bằng PayPal: " + amount + " VND");
    }
}

// Lớp cơ sở Decorator (Bọc phương thức Payment)
abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;

    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }

    @Override
    public void pay(double amount) {
        decoratedPayment.pay(amount);
    }
}

// Decorator: Thêm phí xử lý vào thanh toán
class ProcessingFeeDecorator extends PaymentDecorator {
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double fee = amount * 0.02; // Phí xử lý 2%
        System.out.println("Áp dụng phí xử lý: " + fee + " VND");
        super.pay(amount + fee);
    }
}

// Decorator: Áp dụng mã giảm giá
class DiscountDecorator extends PaymentDecorator {
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double discount = amount * 0.1; // Giảm giá 10%
        System.out.println("Áp dụng mã giảm giá: -" + discount + " VND");
        super.pay(amount - discount);
    }
}

// Chương trình mô phỏng thanh toán với Decorator Pattern
public class DecoratorPatternExample {
    public static void main(String[] args) {
        Payment payment1 = new CreditCardPayment();
        Payment payment2 = new PayPalPayment();

        // Thêm phí xử lý vào thanh toán bằng thẻ tín dụng
        Payment paymentWithFee = new ProcessingFeeDecorator(payment1);
        paymentWithFee.pay(1000);

        System.out.println("--------------------------");

        // Thêm mã giảm giá vào thanh toán PayPal
        Payment paymentWithDiscount = new DiscountDecorator(payment2);
        paymentWithDiscount.pay(2000);

        System.out.println("--------------------------");

        // Kết hợp cả phí xử lý và mã giảm giá vào PayPal
        Payment fullPayment = new ProcessingFeeDecorator(new DiscountDecorator(payment2));
        fullPayment.pay(3000);
    }
}
