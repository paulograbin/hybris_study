package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.catalog.model.ProductReferenceModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.variants.model.VariantProductModel;

import java.util.*;

public class ProductImpexGenerator extends AbstractImpexGenerator<ProductModel> implements ImpexGenerator<ProductModel> {

    public ProductImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        super(impexHeaderGenerationService1);
    }

    @Override
    public Map<String, Set<PK>> makePkMap(ProductModel model) {
        Map<String, Set<PK>> itemTypeToPksMap = new HashMap<>();

        Set<PK> productPks = new HashSet<>();
        productPks.add(model.getPk());
        itemTypeToPksMap.put(ProductModel._TYPECODE, productPks);

        Set<PK> variantPks = new HashSet<>();
        for(VariantProductModel variantProductModel: model.getVariants()) {
            variantPks.add(variantProductModel.getPk());
        }
        itemTypeToPksMap.put(VariantProductModel._TYPECODE, variantPks);

        Set<PK> referencesPks = new HashSet<>();
        for(ProductReferenceModel reference: model.getProductReferences()) {
            referencesPks.add(reference.getPk());
        }
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
