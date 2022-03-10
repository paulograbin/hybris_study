package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public abstract class AbstractImpexGenerator<T extends ItemModel> implements ImpexGenerator<T> {

    private static final Logger LOG = Logger.getLogger(AbstractImpexGenerator.class);

    private static final String LINE_BREAK_CHAR = "\n";
    private static final String WHERE_CLAUSE = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1!} where {pk} in ('%2') \"\" );\"";

    private final ImpexHeaderGenerationService impexHeaderGenerationService;

    public AbstractImpexGenerator(ImpexHeaderGenerationService impexHeaderGenerationService1) {
        this.impexHeaderGenerationService = impexHeaderGenerationService1;
    }


    @Override
    public String printImpex(T model) {
        List<String> typeList = makeTypeToExportList();
        Set<PK> pksForType = makePkList(model);

        StringBuilder sb = new StringBuilder();

        for (String currentType : typeList) {
            if (CollectionUtils.isNotEmpty(pksForType)) {
                String generatedHeader = impexHeaderGenerationService.generateImpexHeaderForType(currentType).orElseThrow(IllegalStateException::new);
                String whereClause = makeWhereClause(currentType, pksForType);
                sb.append(generatedHeader).append(whereClause);
            }
        }

        LOG.info(sb.toString());
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

    private String makeWhereClause(String typeCode, Set<PK> pksToExport) {
        String pks = pksToExport.stream()
                .map(PK::toString)
                .collect(Collectors.joining("','"));

        return WHERE_CLAUSE.replace("%1", typeCode).replace("%2", pks).concat(LINE_BREAK_CHAR);
    }

    public abstract Set<PK> makePkList(T model);

    public List<String> makeTypeToExportList() {
        return Collections.emptyList();
    }
}
