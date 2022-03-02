package com.paulograbin.core.impex;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.servicelayer.impex.ExportResult;

public interface ImpexSpitterFactory {

    ExportResult export(ItemModel itemModel);

    void test();

}
