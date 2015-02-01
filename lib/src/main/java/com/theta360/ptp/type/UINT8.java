package com.theta360.ptp.type;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public final class UINT8 extends LittleEndianInteger {
    // Utility Field

    public static final int SIZE_IN_BYTES = 1;

    public static final UINT8 MIN_VALUE = new UINT8(BigIntegerUtils.minOfUnsigned(SIZE_IN_BYTES));
    public static final UINT8 MAX_VALUE = new UINT8(BigIntegerUtils.maxOfUnsigned(SIZE_IN_BYTES));

    public static final UINT8 ZERO = new UINT8(0);

    // Constructor

    public UINT8(long value) {
        super(value);
    }

    public UINT8(BigInteger value) {
        super(value);
    }

    public UINT8(byte[] bytes) {
        super(bytes);
    }

    // Static Factory Method

    public static UINT8 read(InputStream is) throws IOException {
        byte[] bytes = new byte[SIZE_IN_BYTES];

        if (is.read(bytes) != SIZE_IN_BYTES) {
            throw new EOFException();
        }

        return new UINT8(bytes);
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
