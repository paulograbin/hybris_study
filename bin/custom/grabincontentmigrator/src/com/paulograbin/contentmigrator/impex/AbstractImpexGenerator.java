package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;


public abstract class AbstractImpexGenerator<T extends ItemModel> implements ImpexGenerator<T> {

    private static final Logger LOG = Logger.getLogger(AbstractImpexGenerator.class);
    private static final String NEW_LINE_CHARACTER = "\n";


    private final ImpexHeaderGenerationService impexHeaderGenerationService1;

    public AbstractImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        this.impexHeaderGenerationService1 = impexHeaderGenerationService1;
    }


    @Override
    public String printImpex(T model) {
        String headerForExporModelHierarchy = generateHeaderForAllTypes(model);
        LOG.info(headerForExporModelHierarchy);

        return headerForExporModelHierarchy;
    }

    private String generateHeaderForAllTypes(T model) {
        List<String> typeList = makeTypeToExportList();
        Map<String, Set<PK>> stringSetMap = makePkMap(model);

        StringBuilder sb = new StringBuilder();

        for (String currentType : typeList) {
            Set<PK> pksForType = stringSetMap.get(currentType);
            if(CollectionUtils.isNotEmpty(pksForType)) {
                Optional<String> generatedHeaderOptional = impexHeaderGenerationService1.generateHeaderForTypeFromString(currentType);
                String s = generatedHeaderOptional.orElse("");
                String whereClause = makeWhereClause(pksForType);

                s = s.replace("%2", whereClause);

                sb.append(s).append(NEW_LINE_CHARACTER);
            }
        }

        return sb.toString();
    }

    private String makeWhereClause(Set<PK> pksToExport) {
        return pksToExport.stream()
                .map(PK::toString)
                .collect(Collectors.joining("','"));
    }

    public abstract Set<PK> makePkList(T model);

    public abstract Map<String, Set<PK>> makePkMap(T model);

    public List<String> makeTypeToExportList() {
        return Collections.emptyList();
    }
}
