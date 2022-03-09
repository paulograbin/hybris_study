package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;

import java.util.Set;

public interface ImpexSpitterFactory {

    ExportResult export(ItemModel itemModel);

    ExportResult exportMultiple(Set<ItemModel> itemModel);

    void test();

}
