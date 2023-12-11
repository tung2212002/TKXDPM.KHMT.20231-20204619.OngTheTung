package controller;

import java.sql.SQLException;

import entity.cart.Cart;

public class ViewCartController extends BaseController {

    public void checkAvailabilityOfProduct() throws SQLException {
        Cart.getCart().checkAvailabilityOfProduct();
    }

    public int getCartSubtotal() {
        int subtotal = Cart.getCart().calSubtotal();
        return subtotal;
    }

}
