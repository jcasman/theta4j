/*
 * Copyright (C) 2015 theta4j project
 */

package org.theta4j.ptp.type;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

/**
 * A class represents UINT32 defined in PTP standard.
 */
public final class UINT32 extends PtpInteger {
    // Utility Field

    /**
     * Size of type in bytes.
     */
    public static final int SIZE_IN_BYTES = 4;

    public static final UINT32 MIN_VALUE = new UINT32(BigIntegerUtils.minOfUnsigned(SIZE_IN_BYTES));
    public static final UINT32 MAX_VALUE = new UINT32(BigIntegerUtils.maxOfUnsigned(SIZE_IN_BYTES));

    public static final UINT32 ZERO = new UINT32(0);

    // Constructor

    public UINT32(long value) {
        super(value);
    }

    public UINT32(BigInteger value) {
        super(value);
    }

    public UINT32(byte[] bytes) {
        super(bytes);
    }

    // Static Factory Method

    public static UINT32 read(InputStream is) throws IOException {
        byte[] bytes = new byte[SIZE_IN_BYTES];

        if (is.read(bytes) != SIZE_IN_BYTES) {
            throw new EOFException();
        }

        return new UINT32(bytes);
    }

    // LittleEndianInteger

    @Override
    protected int sizeInBytes() {
        return SIZE_IN_BYTES;
    }

    @Override
    protected boolean isSigned() {
        return false;
    }
}
