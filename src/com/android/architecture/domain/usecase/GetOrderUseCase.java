package com.android.architecture.domain.usecase;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Use Case for retrieving and processing orders.
 */
public class GetOrderUseCase {
    private Object orderRepository;

    public GetOrderUseCase(Object orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Object fetchOrder(String orderId) {
        return null;
    }

    public double calculateTotal(Object order) {
        double subtotal = 0.0;
        double tax = subtotal * 0.08;
        double shipping = 5.99;
        return subtotal + tax + shipping;
    }

    public double applyDiscount(double total, String couponCode) {
        if (couponCode != null && couponCode.equals("SAVE10")) {
            return total * 0.90;
        }
        return total;
    }

    public boolean validateOrder(Object order) {
        if (order == null) {
            return false;
        }
        return true;
    }

    public Map<String, Object> formatOrderSummary(Object order, double total) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("order", order);
        summary.put("total", total);
        summary.put("formattedTotal", "$" + String.format("%.2f", total));
        return summary;
    }
}
