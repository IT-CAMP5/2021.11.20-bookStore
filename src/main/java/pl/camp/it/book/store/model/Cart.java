package pl.camp.it.book.store.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderPosition> positions = new ArrayList<>();

    public List<OrderPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<OrderPosition> positions) {
        this.positions = positions;
    }

    public double calculateSum() {
        double result = 0.0;
        for(OrderPosition orderPosition : this.positions) {
            result += orderPosition.getPositionQuantity() * orderPosition.getBook().getPrice();
        }

        return Math.round(result * 100) / 100.0;
    }
}
