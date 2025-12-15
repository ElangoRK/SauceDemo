@smoke
Feature: Login, Cart, and Checkout validation

  The user should be able to log in, add products to cart,
  validate cart contents, remove products, and complete checkout overview validation.

  Background:
    Given I am on the login page

  Scenario: Validate cart and checkout flow with multiple products

    When I login with the following credentials:
      | Username | standard_user |
      | Password | secret_sauce  |

    Then I should be navigated to the homepage

    When I add the following products to the cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |

    And I navigate to the cart page

    Then I should see "3" products in the cart

    And I should see the following products in the cart:
      | Sauce Labs Backpack      |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |

    When I remove the following products from the cart:
      | Sauce Labs Backpack |

    Then I should see "2" products in the cart

    And I should see the following products in the cart:
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |

    When I click on the checkout button

    And I enter the following checkout details:
      | First Name      | June  |
      | Last Name       | Roy   |
      | Zip/Postal Code | 90001 |

    And I click on the Continue button

    Then I should be on the "Checkout: Overview" page

    And I should see "2" products in the cart

    And I should see the following products in the checkout overview:
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
