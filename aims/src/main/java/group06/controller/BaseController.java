package group06.controller;

import java.util.List;

import group06.entity.cart.Cart;
import group06.entity.cart.CartMedia;
import group06.entity.media.Media;

/**
 * This class is the base controller for our AIMS project
 * 
 * @author nguyenlm
 */
// Không vi phạm SOLID
public class BaseController {

    /**
     * The method checks whether the Media in Cart, if it were in, we will return
     * the CartMedia else return null
     * 
     * @param media
     * @return CartMedia or null
     */
    // data coupling
    // Procedural cohesion
    public CartMedia checkMediaInCart(Media media) {
        return Cart.getCart().checkMediaInCart(media);
    }

    /**
     * This method gets the list of items in cart
     * 
     * @return List[CartMedia]
     */
    // data coupling
    // Procedural cohesion
    public List getListCartMedia() {
        return Cart.getCart().getListMedia();
    }
}
