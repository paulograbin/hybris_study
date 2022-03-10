package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.AbstractItemModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductImpexGenerator extends AbstractImpexGenerator<ProductModel> implements ImpexGenerator<ProductModel> {

    public ProductImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        super(impexHeaderGenerationService1);
    }

    public Map<String, Set<PK>> makePkMap(ProductModel model) {
        Map<String, Set<PK>> itemTypeToPksMap = new HashMap<>();

        Set<PK> productPks = new HashSet<>();
        productPks.add(model.getPk());
        itemTypeToPksMap.put(ProductModel._TYPECODE, productPks);

        Set<PK> variantPks = model.getVariants()
                .stream()
                .map(AbstractItemModel::getPk)
                .collect(Collectors.toSet());
        itemTypeToPksMap.put(VariantProductModel._TYPECODE, variantPks);

        Set<PK> referencesPks = model.getProductReferences()
                .stream()
                .map(AbstractItemModel::getPk)
                .collect(Collectors.toSet());
        itemTypeToPksMap.put(ProductReferenceModel._TYPECODE, referencesPks);

        return itemTypeToPksMap;
    }

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
