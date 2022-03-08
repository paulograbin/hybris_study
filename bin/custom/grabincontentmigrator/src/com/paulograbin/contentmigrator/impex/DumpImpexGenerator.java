package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.model.ItemModel;


public interface DumpImpexGenerator<T extends ItemModel> {

    String generateDump(String itemTypeName);

}
