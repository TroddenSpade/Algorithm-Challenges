package sample;

import java.util.Objects;

public class Order {
    int days;
    int penalty;
    int num;

    Order(int number, int days, int penalty) {
        num = number;
        this.days = days;
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "Order" + num + " {" +
                "days=" + days +
                ", penalty=" + penalty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o || o == null) return true;
        if (getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return num == order.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(days, penalty, num);
    }
}
