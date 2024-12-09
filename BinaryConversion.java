// 12.9
class BinaryFormatException extends Exception {
    public BinaryFormatException(String message) {
        super(message);
    }
}

public class BinaryConversion {

    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        if (!isBinaryString(binaryString)) {
            throw new BinaryFormatException("Invalid binary string: " + binaryString);
        }

        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char bit = binaryString.charAt(i);
            decimalValue = decimalValue * 2 + (bit - '0');
        }
        return decimalValue;
    }

    private static boolean isBinaryString(String binaryString) {
        for (int i = 0; i < binaryString.length(); i++) {
            char bit = binaryString.charAt(i);
            if (bit != '0' && bit != '1') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            String binaryString = "1011";
            System.out.println("The decimal value of " + binaryString + " is: " + bin2Dec(binaryString));

            String invalidBinaryString = "1021";
            System.out.println("The decimal value of " + invalidBinaryString + " is: " + bin2Dec(invalidBinaryString));
        } catch (BinaryFormatException e) {
            System.err.println(e.getMessage());
        }
    }
}