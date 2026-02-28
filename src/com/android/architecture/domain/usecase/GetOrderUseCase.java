package com.android.architecture.domain.usecase;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Use Case for retrieving and processing orders.
 * VIOLATION: Rule 7 - Missing execute() method.
 *   Business logic is scattered across multiple public methods instead of
 *   being consolidated into a single execute() entry point.
 *   To fix this, you must:
 *   (1) Identify which method represents the primary use case action
 *   (2) Rename or consolidate it into execute()
 *   (3) Make the helper methods (calculateTotal, applyDiscount, validateOrder) private
 *   (4) Have execute() orchestrate the full workflow internally
 *   (5) Update all callers to use execute() instead of individual methods
 */
public class GetOrderUseCase {
    private Object orderRepository;

    public GetOrderUseCase(Object orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Object fetchOrder(String orderId) {
        // Fetches an order - should be part of execute()
        return null;
    }

    public double calculateTotal(Object order) {
        // Calculates order total - should be a private helper called by execute()
        double subtotal = 0.0;
        double tax = subtotal * 0.08;
        double shipping = 5.99;
        return subtotal + tax + shipping;
    }

    public double applyDiscount(double total, String couponCode) {
        // Applies discount - should be a private helper called by execute()
        if (couponCode != null && couponCode.equals("SAVE10")) {
            return total * 0.90;
        }
        return total;
    }

    public boolean validateOrder(Object order) {
        // Validates order data - should be a private helper called by execute()
        if (order == null) {
            return false;
        }
        return true;
    }

    public Map<String, Object> formatOrderSummary(Object order, double total) {
        // Formats the order for display - should be a private helper called by execute()
        Map<String, Object> summary = new HashMap<>();
        summary.put("order", order);
        summary.put("total", total);
        summary.put("formattedTotal", "$" + String.format("%.2f", total));
        return summary;
    }
}
