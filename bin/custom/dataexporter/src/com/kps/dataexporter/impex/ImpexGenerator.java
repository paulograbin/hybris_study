package com.kps.dataexporter.impex;

import de.hybris.platform.core.model.ItemModel;

import java.util.Set;


public interface ImpexGenerator<T extends ItemModel> {

    String generateImpex(T model);

    String generateImpex(Set<T> models);
}
