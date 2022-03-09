package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.model.ItemModel;

import java.util.Set;


public interface ImpexGenerator<T extends ItemModel> {

    String printImpex(T model);

    String printImpex(Set<T> models);
}
