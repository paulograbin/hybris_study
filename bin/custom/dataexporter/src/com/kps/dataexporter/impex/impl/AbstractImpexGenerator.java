package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.impex.ImpexGenerator;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.kps.dataexporter.constants.DataexporterConstants.IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_PK_LIST;


public abstract class AbstractImpexGenerator<T extends ItemModel> implements ImpexGenerator<T> {

    private static final Logger LOG = Logger.getLogger(AbstractImpexGenerator.class);

    private static final String LINE_BREAK_CHAR = "\n";

    private ImpexHeaderGenerationService impexHeaderGenerationService;


    @Override
    public String generateImpex(T model) {
        List<String> typeList = makeTypeToExportList();
        Set<PK> pksForType = makePkList(model);

        if (CollectionUtils.isEmpty(pksForType)) {
            return StringUtils.EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        for (String currentType : typeList) {
            String generatedHeader = getImpexHeaderGenerationService().generateImpexHeaderForType(currentType)
                    .orElseThrow(IllegalStateException::new);
            String whereClause = makeWhereClause(currentType, pksForType);
            sb.append(generatedHeader).append(whereClause);
        }

        String result = sb.toString();
        LOG.info(result);
        return result;
    }

    @Override
    public String generateImpex(Set<T> models) {
        List<String> collect = models.stream()
                .map(this::generateImpex)
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

        return IMPEX_EXPORT_FILTER_CLAUSE_USING_TYPE_AND_PK_LIST
                .replace("%1", typeCode).replace("%2", pks).concat(LINE_BREAK_CHAR);
    }

    public abstract Set<PK> makePkList(T model);

    public List<String> makeTypeToExportList() {
        return Collections.emptyList();
    }

    public ImpexHeaderGenerationService getImpexHeaderGenerationService() {
        return impexHeaderGenerationService;
    }

    public void setImpexHeaderGenerationService(ImpexHeaderGenerationService impexHeaderGenerationService) {
        this.impexHeaderGenerationService = impexHeaderGenerationService;
    }
}
