package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class AbstractImpexGenerator<T extends ItemModel> implements ImpexGenerator<T> {

    private static final Logger LOG = Logger.getLogger(AbstractImpexGenerator.class);
    private static final String LINE_BREAK_CHAR = "\n";


    private final ImpexHeaderGenerationService impexHeaderGenerationService1;

    public AbstractImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        this.impexHeaderGenerationService1 = impexHeaderGenerationService1;
    }


    @Override
    public String printImpex(T model) {
        Set<PK> pksToExport = makePkList(model);
        String whereClause = makeWhereClause(pksToExport);

        String headerForExporModelHierarchy = generateHeaderForAllTypes(whereClause);
        LOG.info(headerForExporModelHierarchy);

        return headerForExporModelHierarchy;
    }

    private String generateHeaderForAllTypes(String pksForWhereClause) {
        List<String> typeList = makeTypeToExportList();

        StringBuilder sb = new StringBuilder();

        for (String currentType : typeList) {
            Optional<String> generatedHeaderOptional = impexHeaderGenerationService1.generateHeaderForTypeFromString(currentType);
            String s = generatedHeaderOptional.orElse("");

            s = s.replace("%2", pksForWhereClause);

            sb.append(s).append(LINE_BREAK_CHAR);
        }

        return sb.toString();
    }

    @Override
    public String printImpex(Set<T> models) {
        List<String> collect = models.stream()
                .map(this::printImpex)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();

        for (String s : collect) {
            sb.append(s).append(AbstractImpexGenerator.LINE_BREAK_CHAR);
        }

        return sb.toString();
    }

    private String makeWhereClause(Set<PK> pksToExport) {
        return pksToExport.stream()
                .map(PK::toString)
                .collect(Collectors.joining("','"));
    }

    public abstract Set<PK> makePkList(T model);

    public List<String> makeTypeToExportList() {
        return Collections.emptyList();
    }
}
