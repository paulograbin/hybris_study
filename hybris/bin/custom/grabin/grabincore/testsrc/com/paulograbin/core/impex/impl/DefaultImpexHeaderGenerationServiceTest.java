package com.paulograbin.core.impex.impl;

import org.junit.Before;
import org.junit.Test;

public class DefaultImpexHeaderGenerationServiceTest {

    private ImpexHeaderGenerationService generator;

    @Before
    public void setUp() throws Exception {
        generator = new DefaultImpexHeaderGenerationService(null, null, null);
    }

    @Test
    public void name() {
        String a = "PageTemplate;&Item;active[allownull=true];catalogVersion(catalog(id),version)[unique=true,allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];frontendTemplateName;modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];name;owner(&Item)[forceWrite=true];previewIcon(catalogVersion(catalog(id),version),code);uid[unique=true,allownull=true];velocityTemplate\n" +
                "ContentPage;&Item;approvalStatus(code,itemtype(code))[allownull=true];catalogVersion(catalog(id),version)[unique=true,allownull=true];copyToCatalogsDisabled[allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];defaultPage[allownull=true];description[lang=en];homepage[allownull=true];keywords[lang=en];label;lockedBy(uid);masterTemplate(catalogVersion(catalog(id),version),uid)[allownull=true];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];name;navigationNodeList(catalogVersion(catalog(id),version),uid);onlyOneRestrictionMustApply[allownull=true];originalPage(catalogVersion(catalog(id),version),uid);owner(&Item)[forceWrite=true];pageStatus(code,itemtype(code));previewImage(catalogVersion(catalog(id),version),code);title[lang=en];uid[unique=true,allownull=true]\n" +
                "ContentSlotForTemplate;&Item;allowOverwrite[allownull=true];catalogVersion(catalog(id),version)[unique=true,allownull=true];contentSlot(catalogVersion(catalog(id),version),uid)[allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];owner(&Item)[forceWrite=true];pageTemplate(catalogVersion(catalog(id),version),uid)[allownull=true];position[allownull=true];uid[unique=true,allownull=true]\n" +
                "ContentSlot;&Item;activeFrom[dateformat=dd.MM.yyyy hh:mm:ss];activeUntil[dateformat=dd.MM.yyyy hh:mm:ss];active[allownull=true];catalogVersion(catalog(id),version)[unique=true,allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];currentPosition;modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];name;originalSlot(catalogVersion(catalog(id),version),uid);owner(&Item)[forceWrite=true];uid[unique=true,allownull=true]\n" +
                "ContentSlotForPage;&Item;catalogVersion(catalog(id),version)[unique=true,allownull=true];contentSlot(catalogVersion(catalog(id),version),uid)[allownull=true];creationtime[forceWrite=true,dateformat=dd.MM.yyyy hh:mm:ss];modifiedtime[dateformat=dd.MM.yyyy hh:mm:ss];owner(&Item)[forceWrite=true];page(catalogVersion(catalog(id),version),uid)[allownull=true];position[allownull=true];uid[unique=true,allownull=true]\n";

        String processedheader = generator.processHeaderGenerated("a");
    }
}