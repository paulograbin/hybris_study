package com.paulograbin.core.mostpurchasedproducts;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;
import java.util.Map;

public interface MostPurchasedProductsService {

    MostPurchasedProductsConfiguration loadDefaultConfigurations();

    /**
     * Returns the most purchased products of the given customer.
     * By default system will look to the latest 5 orders to determine up to 10 most purchased products.
     *
     * The most purchased products are determined by the frequency in which the product appears in the orders.
     * In case of products with the same frequency, the purchased quantity will be used to calculate.
     *
     * @param userUid the uid of the user whose orders must be returned
     * @return a {@link List} of {@link ProductModel}
     */
    List<ProductModel> fetchMostPurchasedProductsByCustomer(String userUid, MostPurchasedProductsConfiguration configurations);

    /**
     * Calculates the quantities of each product in the given list of {@link OrderEntryModel}, meaning how many of
     * each product was purchased by the user.
     *
     * @param orderEntries the {@link OrderEntryModel} to analyze
     * @return a {@link Map} with the {@link ProductModel} and the quantities
     */
    Map<ProductModel, Long> calculateProductQuantityOnOrders(List<AbstractOrderEntryModel> orderEntries);

    /**
     * Calculates the frequency of each product in the given set of order entries, meaning the amount of times the products
     * appear in the order entries.
     *
     * @param orderEntries the {@link OrderEntryModel} to analyze
     * @return a {@link Map} with the {@link ProductModel} and the frequency
     */
    Map<ProductModel, Long> calculateProductFrequencyOnOrders(List<AbstractOrderEntryModel> orderEntries);

}
