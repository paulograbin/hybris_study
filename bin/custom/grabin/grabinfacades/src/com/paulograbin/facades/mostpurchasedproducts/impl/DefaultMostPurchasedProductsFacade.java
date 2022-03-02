package com.paulograbin.facades.mostpurchasedproducts.impl;

import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsConfiguration;
import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsService;
import com.paulograbin.facades.mostpurchasedproducts.MostPurchasedProductsData;
import com.paulograbin.facades.mostpurchasedproducts.MostPurchasedProductsFacade;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.format;


public class DefaultMostPurchasedProductsFacade implements MostPurchasedProductsFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMostPurchasedProductsFacade.class);

    private final MostPurchasedProductsService mostPurchasedService;
    private final ProductFacade productFacade;

    protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);


    @Autowired
    public DefaultMostPurchasedProductsFacade(MostPurchasedProductsService mostPurchasedService, ProductFacade productFacade) {
        this.mostPurchasedService = mostPurchasedService;
        this.productFacade = productFacade;
    }

    @Override
    public MostPurchasedProductsData fetchMostPurchasedProductsByCustomer(String userUid) {
        MostPurchasedProductsConfiguration configurations = mostPurchasedService.loadDefaultConfigurations();

        final List<ProductModel> productModels = mostPurchasedService.fetchMostPurchasedProductsByCustomer(userUid, configurations);

        Function<ProductModel, ProductData> convertFromModelToData = pm -> {
            try {
                return productFacade.getProductForCodeAndOptions(pm.getCode(), PRODUCT_OPTIONS);
            } catch (UnknownIdentifierException e) {
                LOG.info(format("Couldn't find product for code %s. Please check if its approved and online", pm.getCode()));
            }

            return null;
        };

        List<ProductData> productWithDetails = productModels.stream()
                .map(convertFromModelToData)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        MostPurchasedProductsData data = new MostPurchasedProductsData();
        data.setMostPurchasedProducts(productWithDetails);

        return data;
    }
}
