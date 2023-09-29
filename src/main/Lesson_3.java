import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lesson_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите данные (Фамилия Имя Отчество dd.mm.yyyy телефон пол):");
        Scanner scanner = new Scanner(System.in);
        String originalData = scanner.nextLine();
        String[] originalDataArr = originalData.split(" ");

        //проверка количества элементов
        if (dataLengthCheck(originalDataArr) == -1) {
            System.out.println("Введено неверное количество аргуменов.");
        } else {
            System.out.println("Количество элементов корректно");
            //проверки правильности форматов данных

            if (birthDateCheck(originalDataArr[3]) && telephoneCheck(originalDataArr[4]) && genderCheck(originalDataArr[5])){
                System.out.println("Форматы данных верны.");
                writeToFile(originalDataArr);
            }
        }
    }

    public static int dataLengthCheck(String[] dataArr){
        if (dataArr.length != 6){
            return -1;
        }
        return 0;
    }

    public static boolean birthDateCheck(String birthDate) throws NumberFormatException{
        boolean dateIsOk = false;
        Integer day = 0;
        Integer month = 0;
        Integer year = 0;

        String[] birthDateArr = birthDate.split("\\.");
        if (birthDateArr.length == 3){
            try {
                day = Integer.parseInt(birthDateArr[0]);
                month = Integer.parseInt(birthDateArr[1]);
                year = Integer.parseInt(birthDateArr[2]);
            } catch (NumberFormatException e){
                System.out.println("Неверный формат даты. Печатай цифры, человек.");
            }

        } else {
            System.out.println("Неверный формат даты. Нужно: dd.mm.yyyy");
        }

        if (day >= 1 && day <= 31 && month >=1 && month <= 12 && year >= 1 && year <= 2023){
            dateIsOk = true;
        } else {
            System.out.println("Дата рождения введена некорректно.");
        }
        return dateIsOk;
    }

    public static boolean telephoneCheck(String telephone) throws NumberFormatException{
        boolean telephoneIsOk = false;
        try {
            Long tel = Long.parseUnsignedLong(telephone);
            //длина с учетом кодов 11 - 13 цифр.
            if (telephone.length() >= 11 && telephone.length() <= 13){
                telephoneIsOk = true;
            } else {
                System.out.println("Неверная длина телефоного номера.");
            }
        } catch (NumberFormatException e1){
            System.out.println("Телефон указан в неверном формате.");
        }
        return telephoneIsOk;
    }
    public static boolean genderCheck(String gender){
        boolean genderIsOk = false;
        if (gender.equals("f") || gender.equals("m")){
            genderIsOk = true;
        } else {
            System.out.println("Пол указан неверно. Используйте f или m.");
        }
        return genderIsOk;
    }

    public static void writeToFile(String[] data) throws IOException {
        String fileName = data[0] + ".txt";
        String tempString = "<";

        for (int i = 0; i < data.length; i++) {
            tempString += data[i];
            tempString += "><";
        }
        tempString =  tempString.substring(0,tempString.length()-1);
        tempString += "\n";

        File file = new File(fileName);

        try {
            if (file.exists()) {
                FileWriter writer = new FileWriter(file, true);
                writer.write(tempString);
                writer.flush();
                writer.close();
                System.out.println("Данные записаны в файл.");
            } else {
                FileWriter writer = new FileWriter(file, false);
                writer.write(tempString);
                writer.flush();
                writer.close();
                System.out.println("Данные записаны в файл.");
            }
        } catch (IOException e2){
            System.out.println("Ошибка записи файла.");
        }
    }
}



