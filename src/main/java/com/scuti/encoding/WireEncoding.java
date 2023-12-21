package com.scuti.encoding;

public class WireEncoding {
    public static byte NEGATIVE = 72;
    public static byte POSITIVE = 73;
    public static int MAX_INTEGER_BYTE_AMOUNT = 6;
    public static int currentTotalBytes;
    public static byte[] encodeInt32(int i)
    {
        byte[] wf = new byte[WireEncoding.MAX_INTEGER_BYTE_AMOUNT];
        int pos = 0;
        int numBytes = 1;
        int startPos = pos;
        int negativeMask = i >= 0 ? 0 : 4;
        i = Math.abs(i);
        wf[pos++] = (byte) (64 + (i & 3));
        for (i >>= 2 ; i != 0; i >>= WireEncoding.MAX_INTEGER_BYTE_AMOUNT)
        {
            numBytes++;
            wf[pos++] = (byte)(64 + (i & 0x3f));
        }
        wf[startPos] = (byte) (wf[startPos] | numBytes << 3 | negativeMask);

        byte[] bzData = new byte[numBytes];
        System.arraycopy(wf, 0, bzData, 0, numBytes);

        return bzData;
    }

    public static int DecodeInt32(byte[] bzData, int totalBytes)
    {
        int pos = 0;
        int v = 0;
        boolean negative = (bzData[pos] & 4) == 4;
        totalBytes = bzData[pos] >> 3 & 7;
        currentTotalBytes = totalBytes;
        v = bzData[pos] & 3;
        pos++;
        int shiftAmount = 2;
        for (int k = 1 ; k < totalBytes ; k++)
        {
            v |= (bzData[pos] & 0x3f) << shiftAmount;
            shiftAmount = 2 + 6 * k;
            pos++;
        }

        if (negative)
            v *= -1;

        return v;
    }
}
