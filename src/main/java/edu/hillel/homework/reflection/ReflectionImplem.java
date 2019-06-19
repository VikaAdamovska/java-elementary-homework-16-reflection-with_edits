package edu.hillel.homework.reflection;

public class ReflectionImplem {
    public static void main(String[] args) {

     /*1) создать аннотацию @Analyzable (может применяться к методам, конструкторам, свойствам класса)
     2) Создать класс реализующий интерфейс:
     interface ICodeAnalyzer {
     void analyzeClass(Class<?> clazz);
     }
     3) метод analyze должен проверять все свойства, методы, конструкторы в классе clazz, и если они помечены аннотацией @Analyzable
     - вывести на консоль имя свойства/метода/конструктора, все аннотации которыми помечен этот элемент
     - если это метод/конструктор - то вывести на консоль имена и типы параметров метода*/

        MyCat barby = new MyCat();
        barby.analyzeClass(MyCat.class);

    }
}


