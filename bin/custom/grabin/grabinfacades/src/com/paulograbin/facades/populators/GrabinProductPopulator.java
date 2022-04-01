/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.paulograbin.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.variants.model.VariantProductModel;
import org.apache.commons.lang3.BooleanUtils;


/**
 * Populates {@link ProductData} with genders
 */
public class GrabinProductPopulator implements Populator<ProductModel, ProductData> {

    @Override
    public void populate(final ProductModel source, final ProductData target) throws ConversionException {
        final ProductModel baseProduct = getBaseProduct(source);

        target.setPentlandString(baseProduct.getPentlandString());
        target.setPentlandInteger(baseProduct.getPentlandInteger() + "");
        target.setPentlandBoolean(BooleanUtils.toStringTrueFalse(baseProduct.isPentlanBoolean()));
        target.setPentlanBoolean((BooleanUtils.toStringTrueFalse(baseProduct.isPentlanBoolean())));
    }

    protected ProductModel getBaseProduct(final ProductModel productModel) {
        ProductModel currentProduct = productModel;
        while (currentProduct instanceof VariantProductModel) {
            final VariantProductModel variant = (VariantProductModel) currentProduct;
            currentProduct = variant.getBaseProduct();
        }
        return currentProduct;
    }
}
