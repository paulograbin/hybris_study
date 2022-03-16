package com.kps.dataexporter.impex;

import java.util.Optional;

public interface ImpexHeaderGenerationService {
    Optional<String> generateImpexHeaderForType(String model);
}
