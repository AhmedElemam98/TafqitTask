package com.elemam.ValleySoftTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Tafqet {
    public static String tafqet(String inputString)
    {
        // Ensure that the number contains only digits
        if (!inputString.matches("^[0-9]+(\\.[0-9]+)?$"))
        {
            return "يجب ادخال قيمه رقميه";
        }
        // Ensure that the number is more than 0
        if(Objects.equals(inputString, "0")||inputString.startsWith("-"))
        {
            return "يجب ادخال قيمه اكبر من الصفر وغير سالبه";
        }

        //To check that if there is قروش or not
        String[] fraction = inputString.split("\\.");

        //Ensure that the number is not more than trillions
        if(fraction[0].length()>15)
        {
            return "يجب ادخال قيمه اقل من 16 رقم سواء جنيهات او قروش ";
        }
        //If there is قروش
        if (fraction.length == 2) {
            if(fraction[1].length()>15)
            {
                return "يجب ادخال قيمه اقل من 16 رقم سواء جنيهات او قروش ";
            }
            String pounds = convertToArabicWords(Long.parseLong(fraction[0])) + " جنيهاً مصرياً و ";
            String piasters = convertToArabicWords(Long.parseLong(fraction[1])) + " قرشاً لا غير ";
            return ("فقط " + pounds + piasters);
        } else if (fraction.length == 1) {
            String pounds = convertToArabicWords(Long.parseLong(fraction[0])) + " جنيهاً مصرياً لا غير";
            return("فقط " + pounds);
        }
        return inputString;
    }

    private static final Map<Integer, String> ones = new HashMap<Integer, String>() {{
        put(0, "صفر");
        put(1, "واحد");
        put(2, "اثنان");
        put(3, "ثلاثة");
        put(4, "أربعة");
        put(5, "خمسة");
        put(6, "ستة");
        put(7, "سبعة");
        put(8, "ثمانية");
        put(9, "تسعة");
        put(10, "عشرة");
        put(11, "أحد عشر");
        put(12, "اثنى عشر");
    }};

    private static final Map<Integer, String> tens = new HashMap<Integer, String>() {{
        put(1, "عشر");
        put(2, "عشرون");
        put(3, "ثلاثون");
        put(4, "أربعون");
        put(5, "خمسون");
        put(6, "ستون");
        put(7, "سبعون");
        put(8, "ثمانون");
        put(9, "تسعون");
    }};

    private static final Map<Integer, String> hundreds = new HashMap<Integer, String>() {{
        put(0, "صفر");
        put(1, "مائة");
        put(2, "مئتان");
        put(3, "ثلاثمائة");
        put(4, "أربعمائة");
        put(5, "خمسمائة");
        put(6, "ستمائة");
        put(7, "سبعمائة");
        put(8, "ثمانمائة");
        put(9, "تسعمائة");
    }};

    private static final Map<Integer, String> thousands = new HashMap<Integer, String>() {{
        put(1, "ألف");
        put(2, "ألفان");
        put(39, "آلاف");
        put(1199, "ألفًا");
    }};

    private static final Map<Integer, String> millions = new HashMap<Integer, String>() {{
        put(1, "مليون");
        put(2, "مليونان");
        put(39, "ملايين");
        put(1199, "مليونًا");
    }};

    private static final Map<Integer, String> billions = new HashMap<Integer, String>() {{
        put(1, "مليار");
        put(2, "ملياران");
        put(39, "مليارات");
        put(1199, "مليارًا");
    }};

    private static final Map<Integer, String> trillions = new HashMap<Integer, String>() {{
        put(1, "تريليون");
        put(2, "تريليونان");
        put(39, "تريليونات");
        put(1199, "تريليونًا");
    }};

    /**
     * This is the main function for Tafqet.
     * @param number The number to convert.
     */
    public static String convertToArabicWords(long number) {

        String value = "";

        // Ensure that the number  is less than or equal 999trillion
        if ( String.valueOf(number).length() <= 15) {
            switch (String.valueOf(number).length()) {
                case 1:
                case 2:
                    value = convertOneTen(number);
                    break;
                case 3:
                    value = convertHundred(number);
                    break;
                case 4:
                case 5:
                case 6:
                    value = convertThousand(number);
                    break;
                case 7:
                case 8:
                case 9:
                    value = convertMillion(number);
                    break;
                case 10:
                case 11:
                case 12:
                    value = convertBillion(number);
                    break;
                case 13:
                case 14:
                case 15:
                    value = convertTrillion(number);
                    break;
            }
        }



        // Remove unnecessary characters.
        return value.replace("وصفر", "").replace("وundefined", "").replaceAll(" +(?= )", "")
                .replace("صفر و", "").replace("صفر", "").replace("مئتان أ", "مائتا أ").replace("مئتان م", "مائتا م");
    }

    /**
     * Converts numbers from 0 to 99
     */
    private static String convertOneTen(long number) {
        String value = "صفر";

        if (number <= 12) {
            if (number == 0) {
                value = ones.get(0);
            } else if (number == 1) {
                value = ones.get(1);
            } else if (number == 2) {
                value = ones.get(2);
            } else if (number == 3) {
                value = ones.get(3);
            } else if (number == 4) {
                value = ones.get(4);
            } else if (number == 5) {
                value = ones.get(5);
            } else if (number == 6) {
                value = ones.get(6);
            } else if (number == 7) {
                value = ones.get(7);
            } else if (number == 8) {
                value = ones.get(8);
            } else if (number == 9) {
                value = ones.get(9);
            } else if (number == 10) {
                value = ones.get(10);
            } else if (number == 11) {
                value = ones.get(11);
            } else if (number == 12) {
                value = ones.get(12);
            }
        } else {
            long first = Long.parseLong(getNth (String.valueOf(number), 0,0));
            long second = Long.parseLong(getNth (String.valueOf(number), 1,1));;
            if(Objects.equals(tens.get((int) first), "عشر")){
                value = ones.get((int)second) + " " + tens.get((int)first);
            }
            else{
                value = ones.get((int)second) + " و" + tens.get((int)first);
            }
        }

        return value;
    }

    /**
     * Converts numbers from 100 to 999
     */
    private static String convertHundred(long number) {
        String value = "";

        while (String.valueOf(number).length() != 3) {
            number = Integer.parseInt("0" + number);
        }

        int first = Character.getNumericValue(String.valueOf(number).charAt(0));
        int remaining = Integer.parseInt(String.valueOf(number).substring(1));

        value = hundreds.get(first) + " و" + convertOneTen(remaining);

        return value;
    }
    private static String convertThousand(long number) {
        return convertThousandsTrillions(thousands.get(1), thousands.get(2), thousands.get(39), thousands.get(1199), 0, number, getNthReverse(String.valueOf(number), 4));
    }

    /**
     * Converts numbers from 1000000 to 999999999
     */
    private static String convertMillion(long number) {
        return convertThousandsTrillions(millions.get(1), millions.get(2), millions.get(39), millions.get(1199), 3, number, getNthReverse(String.valueOf(number), 7));
    }

    /**
     * Converts numbers from 1000000000 to 999999999999
     */
    private static String convertBillion(long number) {
        return convertThousandsTrillions(billions.get(1), billions.get(2), billions.get(39), billions.get(1199), 6, number, getNthReverse(String.valueOf(number), 10));
    }

    /**
     * Converts numbers from 100000000000 to 9999999999999
     */
    private static String convertTrillion(long number) {
        System.out.println(number);

        return convertThousandsTrillions(trillions.get(1), trillions.get(2), trillions.get(39), trillions.get(1199), 9, number, getNthReverse(String.valueOf(number), 13));
    }

    private static String convertThousandsTrillions(String one, String two, String three, String eleven, int diff, long number, String other) {
        other = convertToArabicWords(Long.parseLong(other));
        if (other.isEmpty()) {
            other = "صفر";
        }

        String value = "";
        int numLength = String.valueOf(number).length();
        System.out.println(numLength);
        /*
         * ألوف، أو ملايين، أو مليارات، أو تريليونات
         */
        if (numLength == 4 + diff) {
            int onesDigit = Integer.parseInt(getNth(String.valueOf(number), 0, 0));
            if (onesDigit == 1) {
                value = one + " و" + other;
            } else if (onesDigit == 2) {
                value = two + " و" + other;
            } else {
                value = convertOneTen(onesDigit) + " " + three + " و" + other;
            }
            /**
             * عشرات الألوف، أو عشرات الملايين، أو عشرات المليارات، أو عشرات التريليونات
             */
        } else if (numLength == 5 + diff) {
            int tensDigit = Integer.parseInt(getNth(String.valueOf(number), 0, 1));
            if (tensDigit == 10) {
                value = convertOneTen(tensDigit) + " " + three + " و" + other;
            } else {
                value = convertOneTen(tensDigit) + " " + eleven + " و" + other;
            }
            /**
             *مئات الألوف، أو مئات الملايين، أو مئات المليارات
             */
        } else if (numLength == 6 + diff) {
            int hundredsDigit = Integer.parseInt(getNth(String.valueOf(number), 0, 2));
            int twoDigits = Integer.parseInt(getNth(String.valueOf(number), 1, 2));
            String th = "";
            if (twoDigits == 0) {
                th = one;
            } else {
                th = eleven;
            }
            if (hundredsDigit == 100 || (hundredsDigit >= 101 && hundredsDigit <= 109)) {
                value = convertHundred(hundredsDigit) + " " + th + " و" + other;
            } else if (hundredsDigit == 200 || (hundredsDigit >= 201 && hundredsDigit <= 209)) {
                value = convertHundred(hundredsDigit) + " " + th + " و" + other;
            } else {
                value = convertHundred(hundredsDigit) + " " + th + " و" + other;
            }
        }



        return value;
    }

    /**
     * return The substring of the input string.
     *
     * @param number The input string.
     * @param first  The start index
     * @param end    The end index

     */
    private static String getNth(String number, int first, int end) {
        StringBuilder finalNumber = new StringBuilder();
        for (int i = first; i <= end; i++) {
            finalNumber.append(number.charAt(i));
        }
        return finalNumber.toString();
    }

    /**
     * @return The substring of the input string
     * @param number The input string.
     * @param limit  The number of characters to get from the end.

     */
    private static String getNthReverse(String number, int limit) {
        StringBuilder finalNumber = new StringBuilder();
        int x = 1;
        while (x != limit) {
            finalNumber.insert(0, number.charAt(number.length() - x));
            x++;
        }

        return finalNumber.toString();
    }
}
