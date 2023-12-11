package controller;

import entity.cart.Cart;
import entity.cart.CartMedia;

import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controller.validate.ValidateController;

public class PlaceOrderController extends BaseController {

  private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

  public void placeOrder() throws SQLException {
    Cart.getCart().checkAvailabilityOfProduct();

  }

  public Order createOrder() throws SQLException {
    Order order = new Order();
    for (Object object : Cart.getCart().getListMedia()) {
      CartMedia cartMedia = (CartMedia) object;
      OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), cartMedia.getQuantity(), cartMedia.getPrice());
      order.getlstOrderMedia().add(orderMedia);
    }
    return order;
  }

  public Invoice createInvoice(Order order) {
    return new Invoice(order);
  }

  public int processDeliveryInfo(HashMap<String, String> info)
      throws InterruptedException, IOException {
    LOGGER.info("Process Delivery Info");
    LOGGER.info(info.toString());
    return validateDeliveryInfo(info);
  }

  public int validateDeliveryInfo(HashMap<String, String> info)
      throws InterruptedException, IOException {
    ValidateController valController = new ValidateController();

    if (!valController.validateName(info.get("name"))) {
      System.out.println("Invalid name!");
      JOptionPane.showMessageDialog(null, "Invalid name!",
          "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    if (!valController.validatePhoneNumber(info.get("phone"))) {
      System.out.println("Invalid phone number!");
      JOptionPane.showMessageDialog(null, "Invalid phone number!",
          "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    if (!valController.validateAddress(info.get("address"))) {
      System.out.println("Invalid address!");
      JOptionPane.showMessageDialog(null, "Invalid address!",
          "Error", JOptionPane.ERROR_MESSAGE);
      return 0;
    }
    return 1;
  }

}
