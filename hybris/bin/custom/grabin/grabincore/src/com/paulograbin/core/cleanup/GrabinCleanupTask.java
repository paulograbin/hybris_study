package com.paulograbin.core.cleanup;

import de.hybris.platform.acceleratorservices.dataimport.batch.BatchHeader;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupHelper;
import de.hybris.platform.acceleratorservices.dataimport.batch.task.CleanupTask;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.startsWithIgnoreCase;


public class GrabinCleanupTask extends CleanupTask {

    private CleanupHelper selectedCleanupHelper;

    @Resource
    private MappingRegistry<String, CleanupHelper> customCleanupHelper;

    @Override
    public BatchHeader execute(BatchHeader header) {
        setCleanupHelperForHeader(header);

        if (selectedCleanupHelper != null)
        {
            selectedCleanupHelper.cleanup(header, false);
        }
        else
        {
            super.execute(header);
        }

        return null;
    }

    /**
     * @param header
     *           the customized cleanupHelper to be set based on the file name
     */
    public void setCleanupHelperForHeader(final BatchHeader header)
    {
        final String fileName = header.getFile().getName();
        final Iterator<Map.Entry<String, CleanupHelper>> it = customCleanupHelper.getMappings().entrySet().iterator();
        while (it.hasNext())
        {
            final Map.Entry<String, CleanupHelper> pair = it.next();
            final String key = pair.getKey();
            if (startsWithIgnoreCase(fileName, key))
            {
                selectedCleanupHelper = pair.getValue();
                break;
            }
        }
    }
}
