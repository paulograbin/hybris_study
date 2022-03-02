package com.paulograbin.core.mostpurchasedproducts.impl;

import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderEntryModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.order.payment.PaymentInfoModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.product.UnitModel;
import de.hybris.platform.core.model.type.ComposedTypeModel;
import de.hybris.platform.core.model.user.AddressModel;
import de.hybris.platform.order.InvalidCartException;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.util.DiscountValue;
import de.hybris.platform.util.TaxValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DummyOrderService implements OrderService {

    @Override
    public OrderModel createOrderFromCart(CartModel cart) throws InvalidCartException {
        return null;
    }

    @Override
    public void submitOrder(OrderModel order) {

    }

    @Override
    public OrderModel placeOrder(CartModel cart, AddressModel deliveryAddress, AddressModel paymentAddress, PaymentInfoModel paymentInfo) throws InvalidCartException {
        return null;
    }

    @Override
    public boolean calculateOrder(AbstractOrderModel order) {
        return false;
    }

    @Override
    public OrderEntryModel addNewEntry(OrderModel order, ProductModel product, long qty, UnitModel unit) {
        return null;
    }

    @Override
    public OrderEntryModel addNewEntry(OrderModel order, ProductModel product, long qty, UnitModel unit, int number, boolean addToPresent) {
        return null;
    }

    @Override
    public AbstractOrderEntryModel addNewEntry(ComposedTypeModel entryType, OrderModel order, ProductModel product, long qty, UnitModel unit, int number, boolean addToPresent) {
        return null;
    }

    @Override
    public OrderModel clone(ComposedTypeModel orderType, ComposedTypeModel entryType, AbstractOrderModel original, String code) {
        return null;
    }

    @Override
    public OrderEntryModel getEntryForNumber(OrderModel order, int number) {
        return null;
    }

    @Override
    public List<OrderEntryModel> getEntriesForNumber(OrderModel order, int start, int end) {
        return null;
    }

    @Override
    public List<OrderEntryModel> getEntriesForProduct(OrderModel order, ProductModel product) {
        return null;
    }

    @Override
    public OrderModel saveOrder(OrderModel order) {
        return null;
    }

    @Override
    public void addTotalTaxValue(OrderModel order, TaxValue taxValue) {

    }

    @Override
    public void addAllTotalTaxValues(OrderModel order, List<TaxValue> taxValues) {

    }

    @Override
    public void removeTotalTaxValue(OrderModel order, TaxValue taxValue) {

    }

    @Override
    public void addGlobalDiscountValue(OrderModel order, DiscountValue discountValue) {

    }

    @Override
    public void addAllGlobalDiscountValues(OrderModel order, List<DiscountValue> discountValues) {

    }

    @Override
    public void removeGlobalDiscountValue(OrderModel order, DiscountValue discountValue) {

    }

    @Override
    public DiscountValue getGlobalDiscountValue(OrderModel order, DiscountValue discountValue) {
        return null;
    }
}
