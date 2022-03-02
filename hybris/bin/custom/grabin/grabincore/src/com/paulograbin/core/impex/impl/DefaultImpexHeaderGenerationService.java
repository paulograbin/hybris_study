package com.paulograbin.core.impex.impl;

import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public class DefaultImpexHeaderGenerationService implements ImpexHeaderGenerationService {

    private static final Logger LOG = Logger.getLogger(DefaultImpexHeaderGenerationService.class);

    private ModelService modelService;
    private TypeService typeService;
    private CommonI18NService commonI18NService;

    private String FOOTER = "\"#% impex.exportItemsFlexibleSearch( \"\"select {pk} from {%1} where {pk} in ('%2') \"\" );\"";

    @Autowired
    public DefaultImpexHeaderGenerationService(ModelService modelService, TypeService typeService, CommonI18NService commonI18NService) {
        this.modelService = modelService;
        this.typeService = typeService;
        this.commonI18NService = commonI18NService;
    }

    @Override
    public Optional<String> generateHeaderForType(ItemModel model) {
        HeaderLibraryGenerator generator = new HeaderLibraryGenerator();
        Set<Language> langs = new HashSet<>();
        langs.add(modelService.getSource(commonI18NService.getLanguage("en")));

        Set<ComposedType> composedTypes = new HashSet<>();
        composedTypes.add(modelService.getSource(typeService.getComposedTypeForCode(model.getItemtype())));

        generator.setLanguages(langs);
        generator.setTypes(composedTypes);
        try {
            String s = generator.generateScript();
            String processedHeader = processHeaderGenerated(s);
            String finalHeader = appendWhereClause(processedHeader, model.getItemtype());

            return Optional.of(finalHeader);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<String> generateHeaderForTypeFromString(String model) {
        HeaderLibraryGenerator generator = new HeaderLibraryGenerator();
        Set<Language> langs = new HashSet<>();
        langs.add(modelService.getSource(commonI18NService.getLanguage("en")));

        Set<ComposedType> composedTypes = new HashSet<>();
        composedTypes.add(modelService.getSource(typeService.getComposedTypeForCode(model)));

        generator.setLanguages(langs);
        generator.setTypes(composedTypes);
        try {
            String s = generator.generateScript();
            String processedHeader = processHeaderGenerated(s);
            String finalHeader = appendWhereClause(processedHeader, model);

            return Optional.of(finalHeader);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public Optional<String> generateHeaderForTypes(List<String> itemModelList) {
        HeaderLibraryGenerator generator = new HeaderLibraryGenerator();
        Set<Language> langs = new HashSet<>();
        langs.add(modelService.getSource(commonI18NService.getLanguage("en")));

        Set<ComposedType> composedTypes = new HashSet<>();

        for (String itemModel : itemModelList) {
            composedTypes.add(modelService.getSource(typeService.getComposedTypeForCode(itemModel)));
        }

        generator.setLanguages(langs);
        generator.setTypes(composedTypes);
        try {
            String s = generator.generateScript();
            String processedHeader = processHeaderGenerated(s);
//            String finalHeader = appendWhereClause(processedHeader, model.getItemtype());


            return Optional.of(processedHeader);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private String appendWhereClause(String processedHeader, String typeName) {
        String stepOne = FOOTER.replace("%1", typeName);
        return processedHeader
                .concat(stepOne)
                .concat("\n");
    }

    @Override
    public String processHeaderGenerated(String s) {
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
