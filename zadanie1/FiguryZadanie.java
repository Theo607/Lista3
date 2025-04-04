import java.util.Scanner;

public class FiguryZadanie {
    interface ShapeInterface {
        double area();
        double perimeter();
    }

    static abstract class Shape implements ShapeInterface {
    }

    static class Circle extends Shape {
        double radius;

        Circle(double r) {
            radius = r;
        }

        public double area() {
            return Math.PI * radius * radius;
        }

        public double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    static class Quadrangle extends Shape {
        double a, b, c, d;
        double angle;

        Quadrangle(double a, double b, double c, double d, double angle) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.angle = angle;
        }

        public double area() {
            double s = (a + b + c + d) / 2.0;
            double angleRad = Math.toRadians(angle);
            return Math.sqrt((s - a) * (s - b) * (s - c) * (s - d) - a * c * b * d * Math.pow(Math.cos(angleRad / 2), 2));
        }

        public double perimeter() {
            return a + b + c + d;
        }
    }

    static class Rectangle extends Quadrangle {
        Rectangle(double a, double b) {
            super(a, b, a, b, 90);
        }

        @Override
        public double area() {
            return a * b;
        }
    }

    static class Square extends Quadrangle {
        Square(double a) {
            super(a, a, a, a, 90);
        }

        @Override
        public double area() {
            return a * a;
        }
    }

    static class Rhombus extends Quadrangle {
        Rhombus(double a, double angle) {
            super(a, a, a, a, angle);
        }
    }

    static class Pentagon extends Shape {
        double side;

        Pentagon(double side) {
            this.side = side;
        }

        public double area() {
            return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
        }

        public double perimeter() {
            return 5 * side;
        }
    }

    static class Hexagon extends Shape {
        double side;

        Hexagon(double side) {
            this.side = side;
        }

        public double area() {
            return (3 * Math.sqrt(3) * side * side) / 2;
        }

        public double perimeter() {
            return 6 * side;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shape details (e.g., c 8 8 4 4 90). Type 'exit' to quit.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            String[] parts = input.split(" ");
            String shapeType = parts[0];

            try {
                switch (shapeType) {
                    case "o":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for circle. Expected format: o <radius>");
                        }
                        double radius = Double.parseDouble(parts[1]);
                        Circle c = new Circle(radius);
                        System.out.printf("Circle: Area: %.2f ; Perimeter: %.2f%n", c.area(), c.perimeter());
                        break;

                    case "c":
                        if (parts.length == 3) {
                            double side = Double.parseDouble(parts[1]);
                            double angle = Double.parseDouble(parts[2]);
                            if (angle == 90) {
                                Square s = new Square(side);
                                System.out.printf("Square: Area: %.2f ; Perimeter: %.2f%n", s.area(), s.perimeter());
                            } else {
                                Rhombus r = new Rhombus(side, angle);
                                System.out.printf("Rhombus: Area: %.2f ; Perimeter: %.2f%n", r.area(), r.perimeter());
                            }
                        } else if (parts.length == 4 && parts[3].equals("90")) {
                            double a = Double.parseDouble(parts[1]);
                            double b = Double.parseDouble(parts[2]);
                            Rectangle r = new Rectangle(a, b);
                            System.out.printf("Rectangle: Area: %.2f ; Perimeter: %.2f%n", r.area(), r.perimeter());
                        } else if (parts.length == 6) {
                            double a = Double.parseDouble(parts[1]);
                            double b = Double.parseDouble(parts[2]);
                            double cSide = Double.parseDouble(parts[3]);
                            double d = Double.parseDouble(parts[4]);
                            double angle = Double.parseDouble(parts[5]);
                            Quadrangle q = new Quadrangle(a, b, cSide, d, angle);
                            System.out.printf("Quadrangle: Area: %.2f ; Perimeter: %.2f%n", q.area(), q.perimeter());
                        } else {
                            throw new IllegalArgumentException("Invalid input for quadrangle. Expected format: c <a> <b> <c> <d> <angle>, c <side> <angle> for square or rhombus, or c <a> <b> 90 for rectangle.");
                        }
                        break;

                    case "p":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for pentagon. Expected format: p <side>");
                        }
                        double pentagonSide = Double.parseDouble(parts[1]);
                        Pentagon p = new Pentagon(pentagonSide);
                        System.out.printf("Pentagon: Area: %.2f ; Perimeter: %.2f%n", p.area(), p.perimeter());
                        break;

                    case "s":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for hexagon. Expected format: s <side>");
                        }
                        double hexagonSide = Double.parseDouble(parts[1]);
                        Hexagon h = new Hexagon(hexagonSide);
                        System.out.printf("Hexagon: Area: %.2f ; Perimeter: %.2f%n", h.area(), h.perimeter());
                        break;

                    default:
                        System.out.println("Unknown shape type!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid numbers.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}