package com.paulograbin.facades.mostpurchasedproducts;


public interface MostPurchasedProductsFacade {
    MostPurchasedProductsData fetchMostPurchasedProductsByCustomer(String userUid);
}
