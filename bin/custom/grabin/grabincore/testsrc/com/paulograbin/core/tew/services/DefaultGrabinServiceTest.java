package com.paulograbin.core.tew.services;

import com.paulograbin.core.model.ProjectModel;
import com.paulograbin.core.tew.dao.DefaultProjectDao;
import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.cronjob.CronJobHistoryService;
import de.hybris.platform.servicelayer.cronjob.CronJobService;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


@UnitTest
public class DefaultGrabinServiceTest {

    private GrabinService grabinService;

    @Mock
    private CronJobService cronJobService;

    @Mock
    private CronJobHistoryService cronJobHistoryService;

    @Mock
    private DefaultProjectDao projectDao;

    @Mock
    private DefaultGenericDao<ProjectModel> anotherProjectDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        grabinService = new DefaultGrabinService(cronJobService, cronJobHistoryService);
    }

    @Test
    public void name() {
        Assert.assertNotNull(grabinService);
    }

    @Test
    public void nameAA() {

    }
}