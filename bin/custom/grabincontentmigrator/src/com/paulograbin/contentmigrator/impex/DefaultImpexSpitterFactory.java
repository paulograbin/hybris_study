package com.paulograbin.contentmigrator.impex;

import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import de.hybris.platform.cms2.model.pages.PageTemplateModel;
import de.hybris.platform.core.PK;
import de.hybris.platform.core.model.ItemModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.hac.data.form.ImpexContentFormData;
import de.hybris.platform.impex.enums.ImpExValidationModeEnum;
import de.hybris.platform.impex.model.ImpExMediaModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.impex.ExportConfig;
import de.hybris.platform.servicelayer.impex.ExportResult;
import de.hybris.platform.servicelayer.impex.ExportScriptGenerationService;
import de.hybris.platform.servicelayer.impex.ExportService;
import de.hybris.platform.servicelayer.impex.ImpExValidationResult;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.model.AbstractItemModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;


public class DefaultImpexSpitterFactory implements ImpexSpitterFactory {

    private static final Logger LOG = Logger.getLogger(DefaultImpexSpitterFactory.class);


    @Resource
    private ModelService modelService;

    @Resource
    private ExportService exportService;

    @Resource
    private ImpexHeaderGenerationService impexHeaderGenerationService;

    @Resource
    private ExportScriptGenerationService scriptGenerationService;

    @Resource
    private CommonI18NService commonI18NService;


    private final Map<String, ImpexGenerator> map;

    public DefaultImpexSpitterFactory() {
        map = new HashMap<>(10);

//        registerGenerators();
    }

    private void registerGenerators() {
        map.put(ContentPageModel._TYPECODE, new ContentPageImpexGenerator(impexHeaderGenerationService));
        map.put(PageTemplateModel._TYPECODE, new PageTemplateGenerator(impexHeaderGenerationService));
        map.put(CategoryModel._TYPECODE, new CategoryImpexGenerator(impexHeaderGenerationService));
        map.put(ProductModel._TYPECODE, new ProductImpexGenerator(impexHeaderGenerationService));
    }

    @Override
    public ExportResult export(ItemModel itemModel) {
        registerGenerators();

        ImpexGenerator impexGenerator = map.get(itemModel.getItemtype());
        if (impexGenerator == null) {
            throw new IllegalStateException("No instance of ImpexGenerator configured for item type " + itemModel.getItemtype());
        }
        String s = impexGenerator.printImpex(itemModel);

        ExportResult exportResult = runExport(s);
        printExportResult(exportResult);

        return exportResult;
    }

    @Override
    public void test() {
        registerGenerators();

        long itemToExport = 8796158592048L;
        PK pk = PK.fromLong(itemToExport);

        ItemModel o = modelService.get(pk);
        AbstractItemModel abstractItemModel = o;

//        Optional<String> s1 = impexHeaderGenerationService.generateHeaderForType(o);
//        String s = s1.get();
//        LOG.info("Header geerated " + s);
//        Optional<String> s2 = generateHeaderForType(abstractItemModel);


        ImpexGenerator<ContentPageModel> impexGenerator = map.get(o.getItemtype());
        String s = impexGenerator.printImpex((ContentPageModel) o);

//        PageTemplateModel masterTemplate = ((ContentPageModel) o).getMasterTemplate();
//        map.get(masterTemplate.getItemtype()).printImpex(masterTemplate);

        ExportResult exportResult = runExport(s);
        printExportResult(exportResult);

    }

    private void printExportResult(ExportResult exportResult) {
        ImpExMediaModel exportedData = exportResult.getExportedData();
        ImpExMediaModel exportedMedia = exportResult.getExportedMedia();

        LOG.info(exportResult.isSuccessful());
        LOG.info(exportResult.isError());
        LOG.info("Exported data: " + exportedData.getLocation());
        LOG.info("Exported data: " + "https://electronics.local:9002" + exportedData.getDownloadURL());
        LOG.info("Internal URL: " + exportedData.getInternalURL());
        LOG.info("Size: " + exportedData.getSize());
        LOG.info("Encoding: " + exportedData.getEncoding());
        LOG.info("Extraction id: " + exportedData.getExtractionId());
        LOG.info("Zip entry: " + exportedData.getZipentry());
        LOG.info("Real file name: " + exportedData.getRealFileName());

        LOG.info("Exported media: " + exportedMedia.getLocation());
        LOG.info("Exported media: " + exportedMedia.getSize());
        LOG.info("Exported media: " + "https://electronics.local:9002" + exportedMedia.getDownloadURL());
    }

    private ExportResult runExport(String s) {
        ImpexContentFormData contentFormData = new ImpexContentFormData();
        contentFormData.setEncoding("UTF-8");
        contentFormData.setValidationEnum(ImpExValidationModeEnum.EXPORT_ONLY);

        contentFormData.setScriptContent(s);

        ExportConfig exportConfig = new ExportConfig();
        exportConfig.setSynchronous(true);
        exportConfig.setSingleFile(true);
        exportConfig.setValidationMode(ExportConfig.ValidationMode.STRICT);
        exportConfig.setScript(new StreamBasedImpExResource(new ByteArrayInputStream(contentFormData.getScriptContent().getBytes()), contentFormData.getEncoding()));


        ImpExValidationResult impExValidationResult = this.exportService.validateExportScript(s, null);
        LOG.info("Validation result: " + impExValidationResult.isSuccessful());

        return this.exportService.exportData(exportConfig);
    }
}
