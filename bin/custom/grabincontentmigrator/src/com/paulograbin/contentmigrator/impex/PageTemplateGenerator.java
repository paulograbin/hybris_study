package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class PageTemplateGenerator implements ImpexGenerator<PageTemplateModel> {

    private static final Logger LOG = Logger.getLogger(PageTemplateGenerator.class);

    private final String CONTENT_PAGE_HEADER = "INSERT_UPDATE ContentPage ; catalogVersion(catalog(id),version)[unique=true] ; uid[unique=true] ; name ;  masterTemplate(catalogVersion(catalog(id),version),uid) ; approvalStatus(code) ; description[lang=en] ; homepage ; label ; title[lang=en]";
    private final String CONTENT_PAGE_LINE = "{{1}}           ; {{2}}       ; {{3}} ; {{4}}              ; {{5}}     ; {{6}}      ; {{7}}   ; {{8}} ; {{9}}";
    private final String CONTENT_PAGE_WHERE = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {ContentPage} where {pk} = %1 \"\");\"";


    private final ImpexHeaderGenerationService impexHeaderGenerationService1;

    public PageTemplateGenerator(ImpexHeaderGenerationService impexHeaderGenerationService) {
        impexHeaderGenerationService1 = impexHeaderGenerationService;
    }


    @Override
    public String printImpex(PageTemplateModel model) {
        LOG.info("Generating impex for " + model.getUid() + " of type " + model.getItemtype() + "(" + model.getClass() + ")");

        LOG.info("Uid " + model.getUid());


        List<String> sb = new ArrayList<>();

        sb.add(model.getCatalogVersion().getVersion());
        sb.add(model.getUid());
        sb.add(model.getName());

        String impexLine = String.join("; ", sb);


        LOG.info("****** Impex result ******");
        LOG.info(CONTENT_PAGE_HEADER);
//        LOG.info("; " + impexLine);

        String processedWhereLine = CONTENT_PAGE_WHERE.replaceFirst("%1", model.getPk().toString());
        LOG.info(processedWhereLine);
        LOG.info("****** Impex result ******");
        return impexLine;
    }
}
