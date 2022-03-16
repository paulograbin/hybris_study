package com.kps.dataexporter.factory;

import de.hybris.platform.impex.jalo.exp.generator.HeaderLibraryGenerator;

public interface HeaderLibraryGeneratorFactory {
    HeaderLibraryGenerator createHeaderLibraryGenerator(String typeName);
}
