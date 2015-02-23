package org.theta4j.data;

import org.junit.Test;
import org.theta4j.ptp.type.UINT8;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ChannelNumberTest {
    @Test
    public void value() {
        assertThat(ChannelNumber.RANDOM.value(), is(UINT8.ZERO));
    }
}
