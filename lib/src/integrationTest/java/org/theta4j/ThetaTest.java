/*
 * Copyright (C) 2015 theta4j project
 */

package org.theta4j;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.theta4j.ptp.data.ObjectInfo;
import org.theta4j.ptp.type.UINT32;

import java.io.IOException;
import java.util.List;

public class ThetaTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThetaTest.class);

    private static Theta theta;

    @BeforeClass
    public static void connect() throws IOException {
        theta = new Theta();
    }

    @AfterClass
    public static void close() throws IOException, InterruptedException {
        theta.close();
        Thread.sleep(TestParameters.INTERVAL_MS);
    }

    // Operations

    @Test
    public void getDeviceInfo() throws IOException {
        LOGGER.info("Device Info" + theta.getDeviceInfo());
    }

    @Test
    public void getObjectHandles() throws IOException {
        LOGGER.info("Object Handles: " + theta.getObjectHandles());
    }

    @Test
    public void getObjectInfo() throws IOException {
        List<UINT32> objectHandles = theta.getObjectHandles();
        ObjectInfo objectInfo = theta.getObjectInfo(objectHandles.get(0));
        LOGGER.info("Object Info: " + objectInfo);
    }
}