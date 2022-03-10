package com.paulograbin.contentmigrator.impex;

import com.paulograbin.contentmigrator.service.HardCodedHeaderService;
import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class DefaultImpexHeaderGenerationService implements ImpexHeaderGenerationService {

    private static final Logger LOG = Logger.getLogger(DefaultImpexHeaderGenerationService.class);

    private final ModelService modelService;
    private final TypeService typeService;
    private final CommonI18NService commonI18NService;

    @Resource
    private HardCodedHeaderService hardCodedHeaderService;


    @Autowired
    public DefaultImpexHeaderGenerationService(ModelService modelService, TypeService typeService, CommonI18NService commonI18NService) {
        this.modelService = modelService;
        this.typeService = typeService;
        this.commonI18NService = commonI18NService;
    }

    @Override
    public Optional<String> generateImpexHeaderForType(String typeName) {
        String hardCodedHeaderForItemType = hardCodedHeaderService.findHardCodedHeaderForItemType(typeName);
        if (StringUtils.isNotBlank(hardCodedHeaderForItemType)) {
            LOG.info("Found hardcoded header for type " + typeName);
            return Optional.of(hardCodedHeaderForItemType + "\n");
        }

        LOG.info("Hardcoded header not found, will generated a new one for " + typeName);
        HeaderLibraryGenerator generator = new HeaderLibraryGenerator();
        Set<ComposedType> composedTypes = new HashSet<>();

        generator.setLanguages(getAvailableLanguages());
        generator.setTypes(composedTypes);
        composedTypes.add(modelService.getSource(typeService.getComposedTypeForCode(typeName)));

        String s = generator.generateScript();
        return Optional.of(processHeaderGenerated(s));
    }

    private Set<Language> getAvailableLanguages() {
        //TODO create property to control exported languages
        return commonI18NService.getAllLanguages().stream()
                .map(modelService::getSource)
                .map(Language.class::cast)
                .collect(Collectors.toSet());
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
                .replace("owner(&Item)[forceWrite=true];", "")
                .replace("owner(&Item)[forceWrite=true]", "")
                .replace("modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];", "")
                .replace("creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];", "");
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
}
