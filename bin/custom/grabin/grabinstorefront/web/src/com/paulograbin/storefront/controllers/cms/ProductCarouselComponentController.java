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
package com.paulograbin.storefront.controllers.cms;

import com.paulograbin.facades.mostpurchasedproducts.MostPurchasedProductsData;
import com.paulograbin.facades.mostpurchasedproducts.MostPurchasedProductsFacade;
import com.paulograbin.storefront.controllers.ControllerConstants;
import de.hybris.platform.acceleratorfacades.productcarousel.ProductCarouselFacade;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.ProductSearchFacade;
import de.hybris.platform.commercefacades.search.data.SearchQueryData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.pagedata.PageableData;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Controller for CMS ProductReferencesComponent.
 */
@Controller("ProductCarouselComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.ProductCarouselComponent)
public class ProductCarouselComponentController extends AbstractAcceleratorCMSComponentController<ProductCarouselComponentModel> {
    private static final Logger LOG = LoggerFactory.getLogger(ProductCarouselComponentController.class);

    protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE);

    @Resource(name = "productSearchFacade")
    private ProductSearchFacade<ProductData> productSearchFacade;

    @Resource(name = "productCarouselFacade")
    private ProductCarouselFacade productCarouselFacade;

    @Resource
    private MostPurchasedProductsFacade mostPurchasedProductsFacade;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    protected void fillModel(final HttpServletRequest request, final Model model, final ProductCarouselComponentModel component) {
        LOG.info("Loading carousel " + component.getTitle());

        if (component.isDisplayMostPurchasedProducts()) {
            LOG.info(" ********************************* ");
            LOG.info(" ********************************* ");
            LOG.info(" **** MOST PURCHASED PRODUCTS **** ");
            LOG.info(" ********************************* ");
            LOG.info(" ********************************* ");
        }

        final List<ProductData> products = new ArrayList<>();

        products.addAll(collectLinkedProducts(component));
        products.addAll(collectSearchProducts(component));

        model.addAttribute("title", component.getTitle());
        model.addAttribute("productData", products);
    }

    protected List<ProductData> collectLinkedProducts(final ProductCarouselComponentModel component) {
        if (component.isDisplayMostPurchasedProducts()) {
            MostPurchasedProductsData mostPurchasedProductsData = mostPurchasedProductsFacade.fetchMostPurchasedProductsByCustomer(userService.getCurrentUser().getUid());

            List<ProductData> mostPurchasedProducts = mostPurchasedProductsData.getMostPurchasedProducts();
            mostPurchasedProducts.addAll(productCarouselFacade.collectProducts(component));

            return mostPurchasedProducts;
        }

        return productCarouselFacade.collectProducts(component);
    }

    protected List<ProductData> collectSearchProducts(final ProductCarouselComponentModel component) {
        final SearchQueryData searchQueryData = new SearchQueryData();
        searchQueryData.setValue(component.getSearchQuery());
        final String categoryCode = component.getCategoryCode();

        if (searchQueryData.getValue() != null && categoryCode != null) {
            final SearchStateData searchState = new SearchStateData();
            searchState.setQuery(searchQueryData);

            final PageableData pageableData = new PageableData();
            pageableData.setPageSize(100); // Limit to 100 matching results

            return productSearchFacade.categorySearch(categoryCode, searchState, pageableData).getResults();
        }

        return Collections.emptyList();
    }
}
