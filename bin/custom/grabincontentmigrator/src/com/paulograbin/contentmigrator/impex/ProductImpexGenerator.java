package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.*;

public class ProductImpexGenerator extends AbstractImpexGenerator<ProductModel> implements ImpexGenerator<ProductModel> {

    public ProductImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        super(impexHeaderGenerationService1);
    }

    @Override
    public Map<String, Set<PK>> makePkMap(ProductModel model) {
        return null;
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

        return list;
    }
}
