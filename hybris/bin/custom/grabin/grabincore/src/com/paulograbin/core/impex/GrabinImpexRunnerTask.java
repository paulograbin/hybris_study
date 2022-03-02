package com.paulograbin.core.impex;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.HeaderTask;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.AbstractImpexRunnerTask;
import de.hybris.platform.acceleratorservices.dataimport.batch.util.BatchDirectoryUtils;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.impex.ImpExResource;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.util.CSVConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public abstract class GrabinImpexRunnerTask extends AbstractImpexRunnerTask implements HeaderTask {

    private static final Logger LOG = Logger.getLogger(GrabinImpexRunnerTask.class);


    @Resource
    private ModelService modelService;
    @Resource
    private CronJobService cronJobService;
    @Resource
    private UserService userService;
    @Resource
    private EnumerationService enumerationService;

    private String timeStampFormat;

    private ImportResult importResult = null;


    @Override
    public BatchHeader execute(BatchHeader header) throws FileNotFoundException {
        LOG.info("Starting Grabin impex runner");

        final BatchHeader result = super.execute(header);
        afterExecution(header);
         return result;
    }

    protected void afterExecution(final BatchHeader header) {
        updatedHeaderFilesWithCronJobCode(header);
        writeCronJobLogs(header);
        saveImpexFileOnError(header);


        LOG.info("Finished Hot Folder. Now running cron job");

//        final String feedType = getFeedType(header);
//        if (StringUtils.isNotBlank(feedType))
//        {
//            triggerWorkflowCronjob(BATCH_SURVIVORSHIP_JOB_NAME, feedType);
//        }
    }


    private final String encoding = CSVConstants.HYBRIS_ENCODING;

    private static final String LOG_DIRECTORY = "log";

    /**
     *
     */
    private File getErrorFile(final File file) {
        if (file != null && file.getParentFile() != null && file.getParentFile().getParentFile() != null
                && file.getAbsolutePath() != null) {
            final String relativeDir = BatchDirectoryUtils
                    .verify(BatchDirectoryUtils.getRelativeBaseDirectory(file) + File.separator + LOG_DIRECTORY);

            return new File(relativeDir + file.separator + FilenameUtils.getBaseName(file.getAbsolutePath()) + ".log");
        } else {
            LOG.error("Failed to create error log file for the following hotfolder file processed: " + file.getAbsolutePath());
            return null;
        }
    }


    /**
     * @param header
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    private void writeCronJobLogs(final BatchHeader header) {
        if (importResult != null && !importResult.isError()) {
            return;
        }

        FileOutputStream fileOutputStream = null;
        PrintWriter writer = null;

        try {
            final File errorFile = getErrorFile(header.getFile());
            if (errorFile != null) {
                fileOutputStream = new FileOutputStream(errorFile);
                writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(fileOutputStream, encoding)));
                if (importResult != null && importResult.getUnresolvedLines() != null) {
                    writer.print(importResult.getUnresolvedLines().getPreview());
                }
            }
        } catch (final FileNotFoundException e) {
            LOG.error("Could not write to hot folder log file due to: " + e.getMessage());
        } catch (final UnsupportedEncodingException e) {
            LOG.error("Could not write to hot folder log file due to: " + e.getMessage());
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    /**
     *
     */
    protected void updatedHeaderFilesWithCronJobCode(final BatchHeader header) {
        if (importResult != null && importResult.getCronJob() != null && importResult.isError()) {
            final String cronJobCode = importResult.getCronJob().getCode();
            final File fileWithErrorCode = getFileWithErrorCode(header.getFile(), cronJobCode);
            final boolean fileRenamed = renameFileWithErrorCode(header.getFile(), fileWithErrorCode);
            if (fileRenamed) {
                header.setFile(fileWithErrorCode);
            }

            final List<File> renamedFileList = new ArrayList<File>();
            CollectionUtils.emptyIfNull(header.getTransformedFiles()).stream().forEach(file -> {
                final File transformedFileWithErrorCode = getFileWithErrorCode(file, cronJobCode);
                final boolean transformedFileRenamed = renameFileWithErrorCode(file, transformedFileWithErrorCode);
                if (transformedFileRenamed) {
                    renamedFileList.add(transformedFileWithErrorCode);
                }
            });
            if (CollectionUtils.isNotEmpty(renamedFileList)) {
                header.setTransformedFiles(renamedFileList);
            }
        }
    }

    /**
     *
     */
    protected boolean renameFileWithErrorCode(final File originalFile, final File fileWithErrorCode) {
        boolean fileRenamed = false;
        if (fileWithErrorCode != null) {
            if (!originalFile.renameTo(fileWithErrorCode)) {
                LOG.warn("Could not move " + originalFile + " to " + fileWithErrorCode);
            } else {
                fileRenamed = true;
            }
        }
        return fileRenamed;
    }

    public static final String ERROR_IN_FILE_MARKER = "_er_";

    /**
     * @param cronJobCode
     */
    protected File getFileWithErrorCode(final File file, final String cronJobCode) {
        String fileName = null;
        if (file != null) {
            fileName = file.getAbsolutePath();
        }
        if (StringUtils.isNotEmpty(fileName) && StringUtils.contains(fileName, '.')) {
            return new File(file.getParent() + file.separator + FilenameUtils.getBaseName(fileName)
                    + ERROR_IN_FILE_MARKER + cronJobCode + "." + FilenameUtils.getExtension(fileName));
        }
        LOG.warn("The filename provided was empty or did not contain a '.'. Filename: " + file);
        return null;
    }


    @Override
    protected void processFile(final File file, final String encoding) throws FileNotFoundException {
        LOG.info("Process file...");

        try (FileInputStream fis = new FileInputStream(file)) {
            final ImportConfig config = getImportConfig();
            if (config == null) {
                LOG.error(String.format("Import config not found. The file %s won't be imported.", file == null ? null : file.getName()));
                return;
            }
            final ImpExResource resource = new StreamBasedImpExResource(fis, encoding);
            config.setScript(resource);
            final ImportResult importResult = getImportService().importData(config);
            this.importResult = importResult;

            if (importResult.isError() && importResult.hasUnresolvedLines()) {
                LOG.error(importResult.getUnresolvedLines().getPreview());
            }
        } catch (IOException e) {
            LOG.info("Error processing file " + file, e);
        }
    }

    private void saveImpexFileOnError(final BatchHeader header) {
        if (header != null && header.getFile() != null && header.getFile().getAbsolutePath() != null
                && header.getTransformedFiles() != null && importResult != null) {
            final boolean processingError = importResult.isError();
            LOG.debug("Processing error " + (processingError ? "" : "not ") + "found during cleanup of files");

            if (processingError) {
                //move impex file to error if processing error
                header.getTransformedFiles().forEach(file -> moveFile(file, processingError));
            }
        }
    }

    private static final String DATE_SEPARATOR = "_";

    protected File getDestFile(final File file, final boolean error) {
        final StringBuilder builder = new StringBuilder(file.getName());
        if (!StringUtils.isBlank(timeStampFormat)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(timeStampFormat, Locale.getDefault());
            builder.append(DATE_SEPARATOR);
            builder.append(sdf.format(new Date()));
        }
        return new File(
                error ? BatchDirectoryUtils.getRelativeErrorDirectory(file) : BatchDirectoryUtils.getRelativeArchiveDirectory(file),
                builder.toString());
    }

    /**
     *
     */
    protected void moveFile(final File file, final boolean processingError) {
        final File movedFile = getDestFile(file, processingError);
        LOG.debug("Attempting to move file " + file + " to " + movedFile);

        if (!file.renameTo(movedFile)) {
            LOG.warn("Could not move " + file + " to " + movedFile);
        }
    }

    /**
     * @return the timeStampFormat
     */
    public String getTimeStampFormat() {
        return timeStampFormat;
    }

    /**
     * @param timeStampFormat the timeStampFormat to set
     */
    public void setTimeStampFormat(final String timeStampFormat) {
        this.timeStampFormat = timeStampFormat;
    }
}
