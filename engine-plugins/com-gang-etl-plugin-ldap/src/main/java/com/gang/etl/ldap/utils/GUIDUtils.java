package com.gang.etl.ldap.utils;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @Classname GUID
 * @Description TODO
 * @Date 2020/2/20 14:23
 * @Created by zengzg
 */
public final class GUIDUtils {

    /**
     * Gets GUID as string.
     *
     * @return GUID as string.
     */
    public static String getGuidAsString(byte[] guid) {
        final StringBuilder res = new StringBuilder();

        res.append(addLeadingZero((int) guid[3] & 0xFF));
        res.append(addLeadingZero((int) guid[2] & 0xFF));
        res.append(addLeadingZero((int) guid[1] & 0xFF));
        res.append(addLeadingZero((int) guid[0] & 0xFF));
        res.append("-");
        res.append(addLeadingZero((int) guid[5] & 0xFF));
        res.append(addLeadingZero((int) guid[4] & 0xFF));
        res.append("-");
        res.append(addLeadingZero((int) guid[7] & 0xFF));
        res.append(addLeadingZero((int) guid[6] & 0xFF));
        res.append("-");
        res.append(addLeadingZero((int) guid[8] & 0xFF));
        res.append(addLeadingZero((int) guid[9] & 0xFF));
        res.append("-");
        res.append(addLeadingZero((int) guid[10] & 0xFF));
        res.append(addLeadingZero((int) guid[11] & 0xFF));
        res.append(addLeadingZero((int) guid[12] & 0xFF));
        res.append(addLeadingZero((int) guid[13] & 0xFF));
        res.append(addLeadingZero((int) guid[14] & 0xFF));
        res.append(addLeadingZero((int) guid[15] & 0xFF));

        return res.toString();

    }

    /**
     * Gets GUID as byte array.
     *
     * @return GUID as byte array.
     */
    public static byte[] getGuidAsByteArray(final String guid) {
        final UUID uuid = UUID.fromString(guid);

        final ByteBuffer buff = ByteBuffer.wrap(new byte[16]);
        buff.putLong(uuid.getMostSignificantBits());
        buff.putLong(uuid.getLeastSignificantBits());

        byte[] res = new byte[] { buff.get(3), buff.get(2), buff.get(1), buff.get(0), buff.get(5), buff.get(4),
                buff.get(7), buff.get(6), buff.get(8), buff.get(9), buff.get(10), buff.get(11), buff.get(12),
                buff.get(13), buff.get(14), buff.get(15), };

        return res;
    }

    private static String addLeadingZero(int k) {
        return (k <= 0xF) ? "0" + Integer.toHexString(k) : Integer.toHexString(k);
    }

    private GUIDUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

}
