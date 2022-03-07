package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.model.ItemModel;

import java.util.List;
import java.util.Optional;


public interface ImpexHeaderGenerationService {

    Optional<String> generateHeaderForType(ItemModel model);

    Optional<String> generateHeaderForTypeFromString(String model);

    Optional<String> generateHeaderForTypes(List<String> itemModelList);

}
