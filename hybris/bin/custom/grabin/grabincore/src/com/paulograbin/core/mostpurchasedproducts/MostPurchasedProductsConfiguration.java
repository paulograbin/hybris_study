package com.paulograbin.core.mostpurchasedproducts;


public class MostPurchasedProductsConfiguration {

    private int amountOfOrdersToLookup;
    private int minimumAmountOfProductsToDisplay;
    private int maximumAmountOfProducts;
    private boolean featureEnabled;

    public Boolean isEnabled() {
        return featureEnabled;
    }

    public int getMinimumAmountOfProductsNeededToDisplay() {
        return minimumAmountOfProductsToDisplay;
    }

    public int getMaximumAmountOfProductsToReturn() {
        return maximumAmountOfProducts;
    }

    public int getAmountOfOrdersToLookInto() {
        return amountOfOrdersToLookup;
    }

    public void setAmountOfOrdersToLookup(int amountOfOrdersToLookup) {
        this.amountOfOrdersToLookup = amountOfOrdersToLookup;
    }

    public void setMinimumAmountOfProductsToDisplay(int minimumAmountOfProductsToDisplay) {
        this.minimumAmountOfProductsToDisplay = minimumAmountOfProductsToDisplay;
    }

    public void setMaximumAmountOfProducts(int maximumAmountOfProducts) {
        this.maximumAmountOfProducts = maximumAmountOfProducts;
    }

    public void setFeatureEnabled(boolean featureEnabled) {
        this.featureEnabled = featureEnabled;
    }
}
