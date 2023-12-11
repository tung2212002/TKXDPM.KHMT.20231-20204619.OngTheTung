
import controller.PlaceOrderController;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ValidatePhoneNumberTest {

  private PlaceOrderController placeOrderController;

  
  /** 
   * @throws Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    placeOrderController = new PlaceOrderController();
  }

  
  /** 
   * @param phone
   * @param expected
   */
  @ParameterizedTest
  @CsvSource({ "0123456789,true", "01234,false", "abc123,false", "1234567890,false", })

  public void test(String phone, boolean expected) {
    // when
    boolean isValid = placeOrderController.validatePhoneNumber(phone);

    // then
    assertEquals(expected, isValid);
  }
}
