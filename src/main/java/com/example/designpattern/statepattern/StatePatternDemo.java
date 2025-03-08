package com.example.designpattern.statepattern;
interface OrderState {
    void next(OrderContext context);
    void prev(OrderContext context);
    void printStatus();
}

class ProcessingState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new ShippedState());
    }

    @Override
    public void prev(OrderContext context) {
        System.out.println("The order is in its initial state.");
    }

    @Override
    public void printStatus() {
        System.out.println("Order is being processed.");
    }
}

class ShippedState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new ProcessingState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order has been shipped.");
    }
}


class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context) {
        System.out.println("This order is already delivered.");
    }

    @Override
    public void prev(OrderContext context) {
        context.setState(new ShippedState());
    }

    @Override
    public void printStatus() {
        System.out.println("Order has been delivered.");
    }
}


class OrderContext {
    private OrderState state;

    public OrderContext() {
        // Khởi tạo đơn hàng ở trạng thái "Processing"
        state = new ProcessingState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void nextState() {
        state.next(this);
    }

    public void prevState() {
        state.prev(this);
    }

    public void printStatus() {
        state.printStatus();
    }
}

public class StatePatternDemo {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.printStatus(); // Order is being processed.
        order.nextState();

        order.printStatus(); // Order has been shipped.
        order.nextState();

        order.printStatus(); // Order has been delivered.
        order.prevState();

        order.printStatus(); // Order has been shipped.
    }
}

