package com.impexloader.paulograbin.masterdata;

import de.hybris.platform.impex.constants.ImpExConstants;
import de.hybris.platform.impex.jalo.ImpExManager;
import de.hybris.platform.impex.jalo.ImpExMedia;
import de.hybris.platform.impex.jalo.cronjob.ImpExImportCronJob;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.InputStream;

public class DefaultImpexLoader implements ImpexLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultImpexLoader.class);


    private CronJobService cronJobService;


    @Autowired
    public DefaultImpexLoader(CronJobService cronJobService) {
        this.cronJobService = cronJobService;
    }

    @Override
    public void newMethod() {
        InputStream resourceAsStream = ImpExManager.class.getResourceAsStream("/grabinimpexloader/impexes/teste.impex");

        if (resourceAsStream == null) {
            LOG.info("Could not get resource...");
            return;
        }

        ImpExManager instance = ImpExManager.getInstance();
        ImpExImportCronJob impExImportCronJob = instance.importData(resourceAsStream, "utf-8", false);

        usingImpExImportCronJob();
    }

    private void usingImpExImportCronJob() {
//        ImpExMedia jobMedia = createImpExMedia("myImportScript", "UTF-8");
//        jobMedia.setFieldSeparator(';');
//        jobMedia.setQuoteCharacter('\"');
//        jobMedia.setData(new DataInputStream(ImpExManager.class.getResourceAsStream("myScript.impex")), jobMedia.getCode() + "." + ImpExConstants.File.EXTENSION_CSV, ImpExConstants.File.MIME_TYPE_CSV);
//
//        // create cronjob
//        ImpExImportCronJob cronJob = ImpExManager.getInstance().createDefaultImpExImportCronJob();
//        cronJob.setEnableCodeExecution(false);
//        cronJob.setJobMedia(media);
//
//        // process import
//        cronJob.getJob().perform(cronJob, true);
    }

}
