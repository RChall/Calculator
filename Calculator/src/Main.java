import java.util.Scanner;
public class Main
{
    public static String toArabic(String roman) {
        roman = roman.toUpperCase();
        return switch (roman) {
            case "I" -> "1";
            case "II" -> "2";
            case "III" -> "3";
            case "IV" -> "4";
            case "V" -> "5";
            case "VI" -> "6";
            case "VII" -> "7";
            case "VIII" -> "8";
            case "IX" -> "9";
            case "X" -> "10";
            case "" -> "0";
            default -> roman;
        };
    }

    private static String toRoman (int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        return roman[numArabian];
    }

    public static void main(String[] args) throws Exception {
//Ввод выражения
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: " );
        String vyrazh = in.nextLine(); //Само выражение
        vyrazh = vyrazh.replaceAll(" ","") ;// удаление всех пробелов в выражении
        in.close();
// Объявление переменных
        // Количество символов
        var frstNum = vyrazh; // Первое число
        var scndNum = vyrazh ; //Второе число
        var summa = 0; // Результат
        var what = "deystvie";
        var i = 0; // Только счетчик цикла
        var y = 0; // Второй счетчик внутри цикла
        String sym; // Отдельный симвод в цикле
// Цикл определения знака, действия
        while (i < vyrazh.length()) {
            sym = vyrazh.substring(i,i+1);
            switch (sym) {
                case "+" -> what = "+";
                case "-" -> {
                    what = "-";
                    vyrazh = vyrazh.replace('-', '+');
                }
                case "*" -> {
                    what = "*";
                    vyrazh = vyrazh.replace('*', '+');
                }
                case "/" -> {
                    what = "/";
                    vyrazh = vyrazh.replace('/', '+');
                }
            }
            i += 1;
        }

        i=0;
// Цикл извлечения чисел из выражения с "+"
        while (i < vyrazh.length()) {
            sym = vyrazh.substring(i,i+1);
            if (sym.equals("+") ) {
                y = i;
            }
            i += 1;
            frstNum=vyrazh.substring(0,y);
            scndNum = vyrazh.substring(y+1);
        }

        var firstNum = frstNum;
        var secondNum = scndNum;
        frstNum = toArabic(frstNum);
        scndNum = toArabic(scndNum);

        Integer frstNumInt = Integer.valueOf(frstNum);
        Integer scndNumInt = Integer.valueOf(scndNum);
// Выполнение действия
        switch (what) {
            case "+" -> summa = frstNumInt + scndNumInt;
            case "-" -> summa = frstNumInt - scndNumInt;
            case "*" -> summa = frstNumInt * scndNumInt;
            case "/" -> summa = frstNumInt / scndNumInt;
        }
// Счетчик введенных римских чисел для вывода
        var arabOrRome = 0;
        if (firstNum.equalsIgnoreCase(toRoman(frstNumInt))) {
            arabOrRome +=1;
        }
        if (secondNum.equalsIgnoreCase(toRoman(scndNumInt))) {
            arabOrRome +=1;
        }
        if ( arabOrRome == 2 && summa < 1) {
            arabOrRome +=100500;
            throw new Exception() ;
        }
        if ( frstNumInt > 10 || scndNumInt > 10 || frstNumInt < -10 || scndNumInt < -10 ) {
            arabOrRome +=100500;
            throw new Exception() ;
        }

// Вывод
        switch (arabOrRome) {
            case 2 -> System.out.println("Результат: " + toRoman(summa));
            case 1 -> throw new Exception();

// На самом деле возможно :)
// System.out.println("ОШИБКА!!!Требовалосьввести либо два римских числа, либо два арабских, \n но если что ответ " + summa + " или же " + toRoman(summa)) ;
            case 0 -> System.out.println(" Результат: " + summa);
        }

    }
}