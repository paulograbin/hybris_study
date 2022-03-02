package com.paulograbin.core.impex.impl;

import de.hybris.platform.core.model.ItemModel;

public interface ImpexGenerator<T extends ItemModel> {

    String printImpex(T model);
}
