package com.kps.dataexporter.impex.impl;

import com.kps.dataexporter.factory.HeaderLibraryGeneratorFactory;
import com.kps.dataexporter.impex.ImpexHeaderGenerationService;
import com.kps.dataexporter.service.HardCodedHeaderService;
import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotEmpty;


public class DefaultImpexHeaderGenerationService implements ImpexHeaderGenerationService {

    private static final Logger LOG = Logger.getLogger(DefaultImpexHeaderGenerationService.class);

    @Resource
    private HardCodedHeaderService hardCodedHeaderService;

    @Resource
    private HeaderLibraryGeneratorFactory headerLibraryGeneratorFactory;

    @Override
    public Optional<String> generateImpexHeaderForType(String typeName) {
        checkArgument(isNotEmpty(typeName), "typeName cannot not be empty");

        String hardCodedHeaderForItemType = getHardCodedHeaderService().findHardCodedHeaderForItemType(typeName);
        if (StringUtils.isNotBlank(hardCodedHeaderForItemType)) {
            LOG.info("Found hardcoded header for type " + typeName);
            LOG.info(hardCodedHeaderForItemType);
            return Optional.of(removeColumns(hardCodedHeaderForItemType) + "\n");
        }

        LOG.info("Hardcoded header not found, will generate a new one for " + typeName);
        HeaderLibraryGenerator generator = getHeaderLibraryGeneratorFactory().createHeaderLibraryGenerator(typeName);
        String generatedHeader = generator.generateScript();
        LOG.info("Generated header for " + typeName + ": " + generatedHeader);

        return Optional.of(processHeaderGenerated(generatedHeader));
    }

    private String processHeaderGenerated(String s) {
        final String stepOne = removeFlowerBox(s);
        final String a = appendImpexModifierToLineBeginning(stepOne);

        return removeColumns(a);
    }

    private String removeColumns(String a) {
        return a.replace(",allownull=true", "")
                .replace("[allownull=true]", "")
                .replace("[forceWrite = true]", "")
                .replace("[forceWrite=true]", "")
                .replace("velocityTemplate", "")
                .replace("owner(&Item);", "")
                .replace("owner(&Item)", "")
                .replace("modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];", "")
                .replace("creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];", "")
                .replace("creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];", "")
                .replace("creationtime[forceWrite = true, dateformat = dd.MM.yyyy hh:mm:ss];", "")
                .replace("&Item;", "");
    }

    private String appendImpexModifierToLineBeginning(final String stepOne) {
        String[] split = stepOne.split("\n");
        StringBuilder sb = new StringBuilder();

        final String INSERT_UPDATE = "INSERT_UPDATE ";

        for (String s : split) {
            sb.append(INSERT_UPDATE).append(s).append("\n");
        }

        return sb.toString();
    }

    private String removeFlowerBox(String s) {
        String flowerBox = "#  -------------------------------------------------------\n" +
                "#  Generated header library\n" +
                "#  -------------------------------------------------------\n\n";

        return s.replace(flowerBox, "");
    }

    public HardCodedHeaderService getHardCodedHeaderService() {
        return hardCodedHeaderService;
    }

    public void setHardCodedHeaderService(HardCodedHeaderService hardCodedHeaderService) {
        this.hardCodedHeaderService = hardCodedHeaderService;
    }

    public HeaderLibraryGeneratorFactory getHeaderLibraryGeneratorFactory() {
        return headerLibraryGeneratorFactory;
    }

    public void setHeaderLibraryGeneratorFactory(HeaderLibraryGeneratorFactory headerLibraryGeneratorFactory) {
        this.headerLibraryGeneratorFactory = headerLibraryGeneratorFactory;
    }
}
