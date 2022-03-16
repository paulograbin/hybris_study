package com.kps.dataexporter.factory.impl;

import com.kps.dataexporter.factory.HeaderLibraryGeneratorFactory;
import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;
import de.hybris.platform.jalo.c2l.Language;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.type.TypeService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultHeaderLibraryGeneratorFactory implements HeaderLibraryGeneratorFactory {

    @Resource
    private ModelService modelService;
    @Resource
    private TypeService typeService;
    @Resource
    private CommonI18NService commonI18NService;

    @Override
    public HeaderLibraryGenerator createHeaderLibraryGenerator(String typeName) {
        HeaderLibraryGenerator generator = new HeaderLibraryGenerator();
        Set<ComposedType> composedTypes = new HashSet<>();
        composedTypes.add(modelService.getSource(typeService.getComposedTypeForCode(typeName)));

        generator.setTypes(composedTypes);
        generator.setLanguages(getAvailableLanguages());

        return generator;
    }

    private Set<Language> getAvailableLanguages() {
        //TODO create property to control exported languages
        return commonI18NService.getAllLanguages().stream()
                .map(modelService::getSource)
                .map(Language.class::cast)
                .collect(Collectors.toSet());
    }

}
