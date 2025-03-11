package codo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class NumberToStringConverter {

    private static final byte LATIN1 = 0;
    private static final byte UTF16  = 1;

    public static String numberToString(int num) {
        try {
            Field f = String.class.getDeclaredField("COMPACT_STRINGS");
            f.trySetAccessible();
            boolean COMPACT_STRINGS = (boolean) f.get(null);
            Constructor c = String.class.getDeclaredConstructor(byte[].class, byte.class);
            c.trySetAccessible();

            // This is the JDK11 Integer.toString() implementation with some reflective acceses
            int size = stringSize(num);
            if (COMPACT_STRINGS) {
                byte[] buf = new byte[size];
                getChars(num, size, buf);
                return (String) c.newInstance(buf, LATIN1);
            } else {
                byte[] buf = new byte[size * 2];
                getChars16(num, size, buf);
                return (String) c.newInstance(buf, UTF16);
            }

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static int stringSize(int x) {
        int d = 1;
        if (x >= 0) {
            d = 0;
            x = -x;
        }
        int p = -10;
        for (int i = 1; i < 10; i++) {
            if (x > p)
                return i + d;
            p = 10 * p;
        }
        return 10 + d;
    }

    private static final byte[] DigitTens = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
            '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
            '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
            '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
            '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
            '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
            '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    } ;

    private static final byte[] DigitOnes = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    } ;

    private static int getChars(int i, int index, byte[] buf) {
        int q, r;
        int charPos = index;

        boolean negative = i < 0;
        if (!negative) {
            i = -i;
        }

        // Generate two digits per iteration
        while (i <= -100) {
            q = i / 100;
            r = (q * 100) - i;
            i = q;
            buf[--charPos] = DigitOnes[r];
            buf[--charPos] = DigitTens[r];
        }

        // We know there are at most two digits left at this point.
        q = i / 10;
        r = (q * 10) - i;
        buf[--charPos] = (byte)('0' + r);

        // Whatever left is the remaining digit.
        if (q < 0) {
            buf[--charPos] = (byte)('0' - q);
        }

        if (negative) {
            buf[--charPos] = (byte)'-';
        }
        return charPos;
    }

    private static int getChars16(int i, int index, byte[] buf) {
        int q, r;
        int charPos = index;

        boolean negative = (i < 0);
        if (!negative) {
            i = -i;
        }

        // Get 2 digits/iteration using ints
        while (i <= -100) {
            q = i / 100;
            r = (q * 100) - i;
            i = q;
            putChar(buf, --charPos, DigitOnes[r]);
            putChar(buf, --charPos, DigitTens[r]);
        }

        // We know there are at most two digits left at this point.
        q = i / 10;
        r = (q * 10) - i;
        putChar(buf, --charPos, '0' + r);

        // Whatever left is the remaining digit.
        if (q < 0) {
            putChar(buf, --charPos, '0' - q);
        }

        if (negative) {
            putChar(buf, --charPos, '-');
        }
        return charPos;
    }

    private static final int HI_BYTE_SHIFT;
    private static final int LO_BYTE_SHIFT;
    static {
        try {
            Class c = Class.forName("java.lang.StringUTF16");
            Method m = c.getDeclaredMethod("isBigEndian");
            m.trySetAccessible();
            boolean isBigEndian = (boolean) m.invoke(null);

            if (isBigEndian) {
                HI_BYTE_SHIFT = 8;
                LO_BYTE_SHIFT = 0;
            } else {
                HI_BYTE_SHIFT = 0;
                LO_BYTE_SHIFT = 8;
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void putChar(byte[] val, int index, int c) {
        //assert index >= 0 && index < length(val) : "Trusted caller missed bounds check";
        index <<= 1;
        val[index++] = (byte)(c >> HI_BYTE_SHIFT);
        val[index]   = (byte)(c >> LO_BYTE_SHIFT);
    }
}