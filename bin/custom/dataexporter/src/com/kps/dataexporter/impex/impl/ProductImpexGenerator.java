package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.ImpexGenerator;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.AbstractItemModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.*;
import java.util.stream.Collectors;

public class ProductImpexGenerator extends AbstractImpexGenerator<ProductModel> implements ImpexGenerator<ProductModel> {

    @Override
    public Set<PK> makePkList(ProductModel model) {
        Set<PK> set = new HashSet<>();
        set.add(model.getPk());

        return set;
    }

    @Override
    public List<String> makeTypeToExportList() {
        List<String> list = new ArrayList<>();

        list.add(ProductModel._TYPECODE);
        list.add(VariantProductModel._TYPECODE);
        list.add(ProductReferenceModel._TYPECODE);

        return list;
    }
}
