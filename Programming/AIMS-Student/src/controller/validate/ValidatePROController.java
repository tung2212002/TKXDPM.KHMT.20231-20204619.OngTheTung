package controller.validate;

import entity.order.OrderMedia;

public class ValidatePROController extends ValidateController{
  /**
   * This method check whether media can be placed rush orders or not.
   * 
   * @param orderMedia media which is need to check
   * @return boolean
   */
  public boolean validateSupportedMedia(OrderMedia orderMedia) {
    // orderMedia must not be empty
    if (orderMedia == null) {
      return false;
    }
    return orderMedia.getMedia().isSupport();
  }


  /**
   * This method check whether address can be placed rush orders or not.
   * 
   * @param address address which is need to check
   * @return boolean
   */
  public boolean validateSupportedAddress(String address) {
    address = address.toLowerCase();
    if (address == null || address.isBlank() || address == "null") {
      return false;
    }
    if (address.contains("hanoi") || address.contains("ha noi")) {
      if (address.matches("^[.0-9a-zA-Z\\s,-]+$")) {
        return true;
      }
    }
    return false;
  }
  
}
