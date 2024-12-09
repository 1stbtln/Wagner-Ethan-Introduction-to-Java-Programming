// 13.9
abstract class GeometricObject {
    private String color = "white";
    private boolean filled;

    public GeometricObject() {
    }

    public GeometricObject(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "color: " + color + " and filled: " + filled;
    }
}

public class Circle extends GeometricObject implements Comparable<Circle> {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public int compareTo(Circle other) {
        return Double.compare(this.radius, other.radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return Double.compare(this.radius, other.radius) == 0;
    }

    @Override
    public String toString() {
        return "Circle with radius " + radius + ", area " + getArea() + ", perimeter " + getPerimeter();
    }

    public static void main(String[] args) {
        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(5);
        Circle circle3 = new Circle(3);

        System.out.println("Circle1 equals Circle2: " + circle1.equals(circle2));
        System.out.println("Circle1 equals Circle3: " + circle1.equals(circle3));

        System.out.println("Comparison of Circle1 and Circle3: " + circle1.compareTo(circle3));
    }
}