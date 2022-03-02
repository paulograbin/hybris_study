package com.paulograbin.core.impex.impl;

import de.hybris.platform.core.model.ItemModel;

import java.util.List;
import java.util.Optional;


public class FakeImpexGenerator implements ImpexHeaderGenerationService {

    @Override
    public Optional<String> generateHeaderForType(ItemModel model) {
        return Optional.empty();
    }

    @Override
    public Optional<String> generateHeaderForTypeFromString(String model) {
        return Optional.empty();
    }

    @Override
    public Optional<String> generateHeaderForTypes(List<String> itemModelList) {
        return Optional.empty();
    }

    @Override
    public String processHeaderGenerated(String s) {
        return null;
    }
}
