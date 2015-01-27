package com.theta360.ptpip.packet;

import com.theta360.ptp.io.PtpInputStream;
import com.theta360.ptp.type.UINT32;
import com.theta360.util.ByteUtils;
import com.theta360.util.Validators;

import java.io.IOException;
import java.util.Arrays;

/**
 * Data Packet defined in PTP-IP
 */
public final class DataPacket extends PtpIpPacket {
    private static final int MIN_SIZE_IN_BYTES = UINT32.SIZE_IN_BYTES;

    private final UINT32 transactionID;
    private final byte[] dataPayload;

    // Constructor

    public DataPacket(UINT32 transactionID, byte[] dataPayload) {
        super(Type.DATA);

        Validators.validateNonNull("transactionID", transactionID);
        Validators.validateNonNull("dataPayload", dataPayload);

        this.transactionID = transactionID;
        this.dataPayload = dataPayload.clone();

        super.payload = ByteUtils.join(
                transactionID.bytes(),
                this.dataPayload
        );
    }

    // Getter

    public UINT32 getTransactionID() {
        return transactionID;
    }

    public byte[] getDataPayload() {
        return dataPayload.clone();
    }

    // Static Factory Method

    public static DataPacket read(PtpInputStream pis) throws IOException {
        long length = pis.readUINT32().longValue();
        long payloadLength = length - UINT32.SIZE_IN_BYTES - UINT32.SIZE_IN_BYTES;
        PtpIpPacket.Type type = PtpIpPacket.Type.read(pis);

        PacketUtils.assertType(type, Type.DATA);
        PacketUtils.checkMinLength((int) payloadLength, MIN_SIZE_IN_BYTES);

        long dataLength = payloadLength - UINT32.SIZE_IN_BYTES;              // -TransactionID

        UINT32 transactionID = pis.readUINT32();
        byte[] dataPayload = new byte[(int) dataLength];

        if (pis.read(dataPayload) == -1) {
            throw new IOException();
        }

        return new DataPacket(transactionID, dataPayload);
    }

    // Basic Method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPacket that = (DataPacket) o;

        if (!Arrays.equals(dataPayload, that.dataPayload)) return false;
        if (!transactionID.equals(that.transactionID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionID.hashCode();
        result = 31 * result + Arrays.hashCode(dataPayload);
        return result;
    }

    @Override
    public String toString() {
        return "DataPacket{" +
                "transactionID=" + transactionID +
                ", dataPayload=" + Arrays.toString(dataPayload) +
                '}';
    }
}
