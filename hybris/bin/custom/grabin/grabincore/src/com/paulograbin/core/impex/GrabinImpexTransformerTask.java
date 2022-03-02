package com.paulograbin.core.impex;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.converter.ImpexConverter;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.ImpexTransformerTask;
import de.hybris.platform.util.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class GrabinImpexTransformerTask extends ImpexTransformerTask {

    private static final Logger LOG = LoggerFactory.getLogger(GrabinImpexTransformerTask.class);

    @Override
    public BatchHeader execute(BatchHeader header) throws UnsupportedEncodingException, FileNotFoundException {
        LOG.info("EXECUTE!!!");
        LOG.info("EXECUTE!!!");
        LOG.info("EXECUTE!!!");

        return super.execute(header);
    }

    @Override
    protected boolean convertFile(BatchHeader header, File file, File impexFile, ImpexConverter converter) throws UnsupportedEncodingException, FileNotFoundException {
        LOG.info("CONVERT FILE!!!");
        LOG.info("CONVERT FILE!!!");
        LOG.info("CONVERT FILE!!!");

         return super.convertFile(header, file, impexFile, converter);
    }

    @Override
    protected void buildReplacementSymbols(Map<String, String> symbols, BatchHeader header, ImpexConverter converter) {
        LOG.info("buildReplacementSymbols!!!");
        LOG.info("buildReplacementSymbols!!!");
        LOG.info("buildReplacementSymbols!!!");

        super.buildReplacementSymbols(symbols, header, converter);
    }

    @Override
    protected PrintWriter writeErrorLine(File file, CSVReader csvReader, PrintWriter errorWriter, IllegalArgumentException exc) throws UnsupportedEncodingException, FileNotFoundException {
        LOG.info("writeErrorLine!!!");
        LOG.info("writeErrorLine!!!");
        LOG.info("writeErrorLine!!!");

        return super.writeErrorLine(file, csvReader, errorWriter, exc);
    }

    @Override
    protected CSVReader createCsvReader(File file) throws UnsupportedEncodingException, FileNotFoundException {
        LOG.info("createCsvReader!!!");
        LOG.info("createCsvReader!!!");
        LOG.info("createCsvReader!!!");

        return super.createCsvReader(file);
    }
}
