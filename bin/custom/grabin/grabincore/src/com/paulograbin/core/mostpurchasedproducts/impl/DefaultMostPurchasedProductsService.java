package com.paulograbin.core.mostpurchasedproducts.impl;

import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsConfiguration;
import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.store.BaseStoreModel;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class DefaultMostPurchasedProductsService implements MostPurchasedProductsService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultMostPurchasedProductsService.class);

    public static final int DEFAULT_ORDER_LOOKUP_QUANTITY = 5;
    public static final int DEFAULT_MAXIMUM_AMOUNT_OF_MOST_RECOMMENDED_PRODUCTS = 10;
    public static final int DEFAULT_MINIMUM_AMOUNT_PRODUCTS_TO_DISPLAY = 3;
    public static final Boolean MOST_PURCHASED_PRODUCTS_FEATURE_ENABLED = false;


    private final OrderService orderService;
    private final UnitService unitService;
    private final BaseStoreService baseStoreService;
    private final ProductService productService;
    private final CatalogVersionService catalogVersionService;


    static {
        LOG.info("Starting up!!!!");
    }

    @Autowired
    public DefaultMostPurchasedProductsService(OrderService orderService, UnitService unitService, BaseStoreService baseStoreService, ProductService productService, CatalogVersionService catalogVersionService) {
        this.orderService = orderService;
        this.unitService = unitService;
        this.baseStoreService = baseStoreService;
        this.productService = productService;
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    public MostPurchasedProductsConfiguration loadDefaultConfigurations() {
        MostPurchasedProductsConfiguration configurations = new MostPurchasedProductsConfiguration();

        BaseStoreModel currentBaseStore = baseStoreService.getCurrentBaseStore();

        configurations.setFeatureEnabled(defaultIfNull(currentBaseStore.isMostPurchasedProductsFeatureSwitch(), MOST_PURCHASED_PRODUCTS_FEATURE_ENABLED));
        configurations.setMaximumAmountOfProducts(defaultIfNull(currentBaseStore.getMaxAmountOfMostPurchasedProducts(), DEFAULT_MAXIMUM_AMOUNT_OF_MOST_RECOMMENDED_PRODUCTS));
        configurations.setMinimumAmountOfProductsToDisplay(defaultIfNull(currentBaseStore.getMinimumAmountOfProductsNeededToShow(), DEFAULT_MINIMUM_AMOUNT_PRODUCTS_TO_DISPLAY));
        configurations.setAmountOfOrdersToLookup(defaultIfNull(currentBaseStore.getAmountOfOrdersToConsider(), DEFAULT_ORDER_LOOKUP_QUANTITY));

        return configurations;
    }

    @Override
    public List<ProductModel> fetchMostPurchasedProductsByCustomer(String userUid, MostPurchasedProductsConfiguration configurations) {
        if (isFalse(configurations.isEnabled())) {
            LOG.info("Most purchased products feature is disabled. If you wish to make it enabled again please use Backoffice");
            return Collections.emptyList();
        }

        Asserts.notNull(userUid, "User uid cannot be null");


        Collection<CatalogVersionModel> sessionCatalogVersions = catalogVersionService.getSessionCatalogVersions();
        if (sessionCatalogVersions.size() == 1) {
            List<ProductModel> allProductsForCatalogVersion = productService.getAllProductsForCatalogVersion(sessionCatalogVersions.stream().findFirst().get());

            return allProductsForCatalogVersion.stream().limit(10).collect(Collectors.toList());
        }


        List<ProductModel> productsToReturn = new ArrayList<>();
        CatalogVersionModel electronicsCatalogOnline = catalogVersionService.getCatalogVersion("electronicsProductCatalog", "Online");
        productsToReturn.add(productService.getProductForCode(electronicsCatalogOnline, "149243"));
//        productsToReturn.add(productService.getProductForCode(electronicsCatalogOnline, "23210"));
        productsToReturn.add(productService.getProductForCode(electronicsCatalogOnline, "301233"));

        return productsToReturn;

//
//        final List<OrderModel> latestOrdersFromUser = Collections.emptyList();
//
//        final List<AbstractOrderEntryModel> orderEntries = getAllEntriesForOrders(latestOrdersFromUser);
//
//        final Map<ProductModel, Long> productFrequency = calculateProductFrequencyOnOrders(orderEntries);
//        printMapToConsole("Frequencies: ", productFrequency);
//        final Map<ProductModel, Long> productQuantities = calculateProductQuantityOnOrders(orderEntries);
//        printMapToConsole("Quantities: ", productQuantities);
//
//        final List<ProductModel> mostPurchasedProducts = sortMostPurchasedProducts(productFrequency, productQuantities);
//        printListToConsole("Most purchased: ", mostPurchasedProducts);
//
//        List<ProductModel> finalMostPurchasedList = mostPurchasedProducts.stream()
//                .limit(configurations.getMaximumAmountOfProductsToReturn())
//                .collect(toList());
//
//        if (finalMostPurchasedList.size() >= configurations.getMinimumAmountOfProductsNeededToDisplay()) {
//            LOG.debug("Returning " + finalMostPurchasedList.size() + " most purchased products for user " + userUid +
//                    " as the minimum amount needed was " + configurations.getMinimumAmountOfProductsNeededToDisplay() +
//                    " and the maximum was " + configurations.getMaximumAmountOfProductsToReturn());
//            return finalMostPurchasedList;
//        } else {
//            LOG.debug("Not enough products found for " + userUid + ", won't show carousel.");
//            return Collections.emptyList();
//        }
    }


    @Override
    public Map<ProductModel, Long> calculateProductQuantityOnOrders(final List<AbstractOrderEntryModel> orderEntries) {
        LOG.debug("Calculating product quantities");

        List<AbstractOrderEntryModel> entriesWithSupportedUnits = discardProductsNotPurchasedByWeightOrPieces(orderEntries);

        Map<ProductModel, Long> productsByPiecesCalculated = handleProductsByPiece(entriesWithSupportedUnits);
        printMapToConsole("Products by pieces calculated: ", productsByPiecesCalculated);

        return addMapsTogetherSummingValues(new HashMap<>(), productsByPiecesCalculated);
    }

    private Map<ProductModel, Long> addMapsTogetherSummingValues(Map<ProductModel, Long> collect, Map<ProductModel, Long> collect1) {
        return Stream.concat(collect.entrySet().stream(), collect1.entrySet().stream())
                .collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Long::sum
                ));
    }

    private List<AbstractOrderEntryModel> discardProductsNotPurchasedByWeightOrPieces(final List<AbstractOrderEntryModel> orderEntries) {
        return orderEntries;

//        final UnitModel pce = unitService.getUnitForCode("PCE");
//        final UnitModel pieces = unitService.getUnitForCode("pieces");
//        final UnitModel kgm = unitService.getUnitForCode("KGM");
//
//        return orderEntries.stream()
//                .filter(oe -> oe.getUnit() == pce ||
//                        oe.getUnit() == pieces ||
//                        oe.getUnit() == kgm)
//                .collect(Collectors.toList());
    }

    private Map<ProductModel, Long> handleProductsByPiece(List<AbstractOrderEntryModel> entriesWithSupportedUnits) {
        List<AbstractOrderEntryModel> productsByPieces = entriesWithSupportedUnits.stream()
                .filter(oe -> oe.getUnit().getCode().equalsIgnoreCase("PCE") ||
                        oe.getUnit().getCode().equalsIgnoreCase("pieces"))
                .collect(toList());

        return productsByPieces.stream()
                .collect(Collectors.groupingBy(AbstractOrderEntryModel::getProduct,
                        Collectors.summingLong(AbstractOrderEntryModel::getQuantity)));
    }

    private void printMapToConsole(final String prefixMessage, final Map<ProductModel, Long> productFrequency) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(prefixMessage);

            for (Map.Entry<ProductModel, Long> productModelLongEntry : productFrequency.entrySet()) {
                LOG.debug(productModelLongEntry.getKey().getCode() + " - " + productModelLongEntry.getValue());
            }
        }
    }

    @Override
    public Map<ProductModel, Long> calculateProductFrequencyOnOrders(final List<AbstractOrderEntryModel> orderEntries) {
        LOG.debug("Calculating product frequencies");

        return orderEntries.stream()
                .map(AbstractOrderEntryModel::getProduct)
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));
    }
}
