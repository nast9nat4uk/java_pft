package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + "и " + r.b + " = " + r.area());

        Point p1 = new Point(3,2);
        Point p2 = new Point (5,6);
        System.out.println("Расстояние между точками x и " + "y равно " + distance(p1, p2));


    }


    public static double distance(Point p1, Point p2) {

        double sumx=(p1.x-p2.x)*(p1.x-p2.x); // квадрат разности координат x
        double sumy = (p1.y-p2.y)*(p1.y-p2.y); //квадрат разности координат Y
        double sum = sumx+sumy;
        return  Math.sqrt(sum);

    }

    public static void hello(String somebody) {

        System.out.println("Hello," + somebody + "!");
    }




}