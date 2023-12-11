package controller;

import entity.order.Order;
import entity.order.OrderMedia;
import java.util.List;
import controller.calculate.CalculateRushShippingFee_v1;
import controller.validate.ValidatePROController;

public class PlaceRushOrderController extends BaseController {

  private int total = 0;

  public int placeRushOrder(Order order, String address) {
    List<OrderMedia> lstOrderMedia = order.getlstOrderMedia();
    ValidatePROController valCtrl = new ValidatePROController();
    if (!valCtrl.validateSupportedAddress(address)) {
      System.out.println("This address is not supported to rush order!");
      return 0;
    }
    if (lstOrderMedia == null) {
      return 0;
    }
    CalculateRushShippingFee_v1 calCtrl = new CalculateRushShippingFee_v1();
    for (OrderMedia i : lstOrderMedia) {
      if (!valCtrl.validateSupportedMedia(i)) {
        System.out.println("This media is not support: " + i.getMedia().getTitle());
      } else {
        total += calCtrl.calculateRushOrderShippingFee(i);
      }
    }
    return total;
  }
}
