package com.paulograbin.core.mostpurchasedproducts;

import de.hybris.platform.core.model.product.ProductModel;

public class ProductStatistics
{

    private final ProductModel product;
    private final long frequency;
    private final long quantity;

    public ProductStatistics(ProductModel productCode, long frequency, long quantity)
    {
        this.product = productCode;
        this.frequency = frequency;
        this.quantity = quantity;
    }

    public ProductModel getProduct()
    {
        return product;
    }

    public long getFrequency()
    {
        return frequency;
    }

    public long getQuantity()
    {
        return quantity;
    }

    @Override
    public String toString()
    {
        return "{" +
                "code='" + product.getCode() +
                ", freq=" + frequency +
                ", quant=" + quantity +
                '}';
    }
}
