package com.paulograbin.core.mostpurchasedproducts.impl;

import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsConfiguration;
import com.paulograbin.core.mostpurchasedproducts.MostPurchasedProductsService;
import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.order.OrderService;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.UnitService;
import de.hybris.platform.store.services.BaseStoreService;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DefaultMostPurchasedProductsServiceTest {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultMostPurchasedProductsServiceTest.class);

    private final static String USER_UID = "plgrabin@gmail.com";
    private final static String USER_UID_2 = "paulo.grabin@sap.com";

    private static final String WINE_CODE = "000000000003002179";
    private static final String TEQUILA_CODE = "000000000003062507";
    private static final String MANZANA_CODE = "000000000003102803";
    private static final String BIKE_CODE = "000000000003321121";
    private static final String MANGO_CODE = "000000000003040674";
    private static final String WATER_CODE = "000000000003041386";
    private static final String TV_CODE = "000000000003421428";
    private static final String XBOX_CODE = "000000000003430769";
    private static final String VASELINA_CODE = "000000000003261867";
    private static final String MILK_CODE = "000000000003019407";
    private static final String JACK_DANIELS_CODE = "000000000003002305";
    private static final String PRINTER_CARTRIDGE = "000000000003242247";


    private MostPurchasedProductsConfiguration configurations;
    private MostPurchasedProductsService mostPurchasedService;


    private OrderService orderService;
    private UnitService unitService;
    private BaseStoreService baseStoreService;
    private ProductService productsService;
    private CatalogVersionService catalogVersionService;


    @Before
    public void setUp() throws Exception {
        configurations = new MostPurchasedProductsConfiguration();
        configurations.setFeatureEnabled(true);
        configurations.setAmountOfOrdersToLookup(5);
        configurations.setMinimumAmountOfProductsToDisplay(3);
        configurations.setMaximumAmountOfProducts(10);

        orderService = new DummyOrderService();
        unitService = new DummyUnitService();
        baseStoreService = new DummyBaseStoreService();
        catalogVersionService = new DummyCatalogVersionService();


        mostPurchasedService = new DefaultMostPurchasedProductsService(orderService, unitService, baseStoreService, productsService, catalogVersionService);
    }

    @Test
    public void ifDisabled__noProductsAreReturned() {
        configurations.setFeatureEnabled(false);

        List<ProductModel> productModels = mostPurchasedService.fetchMostPurchasedProductsByCustomer(USER_UID, configurations);

        assertEquals(0, productModels.size());
    }

    @Test
    public void checkNoMoreThan10ProductsAreReturned() {
        List<ProductModel> productModels = mostPurchasedService.fetchMostPurchasedProductsByCustomer(USER_UID, configurations);

        assertTrue(productModels.size() <= configurations.getMaximumAmountOfProductsToReturn());
    }


    @Test
    public void testProductsAreNotReturnedIfLessThanMinimumAmount() {
        List<ProductModel> mostPurchasedProducts = mostPurchasedService.fetchMostPurchasedProductsByCustomer(USER_UID_2, configurations);

        assertEquals(0, mostPurchasedProducts.size());
    }

    @Test
    public void testProductsAreInMostPurchasedList() {
        List<ProductModel> productModels = mostPurchasedService.fetchMostPurchasedProductsByCustomer(USER_UID, configurations);

        assertTrue(productIsInList(productModels, WINE_CODE));
        assertTrue(productIsInList(productModels, TEQUILA_CODE));
        assertTrue(productIsInList(productModels, MANZANA_CODE));
        assertFalse(productIsInList(productModels, BIKE_CODE));
    }

    private boolean productIsInList(List<ProductModel> productModels, String productCode) {
        LOG.info(format("Looking for product %s in the most purchased products", productCode));

        for (ProductModel productModel : productModels) {
            if (productModel.getCode().equalsIgnoreCase(productCode)) {
                return true;
            }
        }

        return false;
    }
}