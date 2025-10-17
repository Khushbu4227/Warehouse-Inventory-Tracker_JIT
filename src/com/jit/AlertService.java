package com.jit;

public class AlertService implements StockObserver {
	public void onLowStock(Product product) {
   // Show Alert on Low Stock
		 try {
	            if (product == null) {
	                throw new NullPointerException("Product cannot be null while sending alert!");
	            }

	            System.out.println(" ALERT: Low stock for " + product.getName() +
	                    " — only " + product.getQuantity() + " left (Threshold: " +
	                    product.getThreshold() + ")");
	        } catch (Exception e) {
	            System.err.println("Error in AlertService: " + e.getMessage());
	        }
}
}
