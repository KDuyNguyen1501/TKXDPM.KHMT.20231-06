package group06.controller;

import java.sql.SQLException;

import group06.entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 * 
 * @author nguyenlm
 */
// Không vi phạm SOLID
public class ViewCartController extends BaseController {

    /**
     * This method checks the available products in Cart
     * 
     * @throws SQLException
     */
    // data coupling
    // Procedural cohesion
    //
    public void checkAvailabilityOfProduct() throws SQLException {
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     * 
     * @return subtotal
     */
    // data coupling
    // Procedural cohesion
    public int getCartSubtotal() {
        int subtotal = Cart.getCart().calSubtotal();
        return subtotal;
    }

}
