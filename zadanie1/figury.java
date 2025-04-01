package zadanie1;

import java.util.Scanner;

public class figury {
    interface shape {
        double area();
        double perimeter();
    }

    class circle implements shape {
        int radius;

        circle(int r) {
            radius = r;
        }

        public double area() {
            return Math.PI * radius * radius;
        }

        public double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    class quadrangle implements shape {
        int a, b, c, d;
        double angle; // Angle in degrees

        quadrangle(int a, int b, int c, int d, double angle) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.angle = angle;
        }

        public double area() {
            // Using Bretschneider's formula for area of a quadrilateral
            double s = (a + b + c + d) / 2.0; // Semi-perimeter
            double angleRad = Math.toRadians(angle);
            return Math.sqrt((s - a) * (s - b) * (s - c) * (s - d) - a * c * b * d * Math.pow(Math.cos(angleRad / 2), 2));
        }

        public double perimeter() {
            return a + b + c + d;
        }
    }

    class rectangle extends quadrangle {
        rectangle(int a, int b) {
            super(a, b, a, b, 90);
        }
    }

    class square extends quadrangle {
        square(int a) {
            super(a, a, a, a, 90);
        }
    }

    class rhombus extends quadrangle {
        rhombus(int a, double angle) {
            super(a, a, a, a, angle);
        }
    }

    class pentagon implements shape {
        int side;

        pentagon(int side) {
            this.side = side;
        }

        public double area() {
            // Area of a regular pentagon
            return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
        }

        public double perimeter() {
            return 5 * side;
        }
    }

    class hexagon implements shape {
        int side;

        hexagon(int side) {
            this.side = side;
        }

        public double area() {
            // Area of a regular hexagon
            return (3 * Math.sqrt(3) * side * side) / 2;
        }

        public double perimeter() {
            return 6 * side;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shape details (e.g., c 8 8 4 4 90):");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String shapeType = parts[0];
            figury f = new figury();

            switch (shapeType) {
                case "o": // Circle
                    int radius = Integer.parseInt(parts[1]);
                    circle c = f.new circle(radius);
                    System.out.printf("Circle: Area = %.2f, Perimeter = %.2f%n", c.area(), c.perimeter());
                    break;

                case "c": // Quadrangle
                    if (parts.length == 3) { // Square
                        int side = Integer.parseInt(parts[1]);
                        square s = f.new square(side);
                        System.out.printf("Square: Area = %.2f, Perimeter = %.2f%n", s.area(), s.perimeter());
                    } else { // General quadrangle
                        int a = Integer.parseInt(parts[1]);
                        int b = Integer.parseInt(parts[2]);
                        int cSide = Integer.parseInt(parts[3]);
                        int d = Integer.parseInt(parts[4]);
                        double angle = Double.parseDouble(parts[5]);
                        quadrangle q = f.new quadrangle(a, b, cSide, d, angle);
                        System.out.printf("Quadrangle: Area = %.2f, Perimeter = %.2f%n", q.area(), q.perimeter());
                    }
                    break;

                case "p": // Pentagon
                    int pentagonSide = Integer.parseInt(parts[1]);
                    pentagon p = f.new pentagon(pentagonSide);
                    System.out.printf("Pentagon: Area = %.2f, Perimeter = %.2f%n", p.area(), p.perimeter());
                    break;

                case "s": // Hexagon
                    int hexagonSide = Integer.parseInt(parts[1]);
                    hexagon h = f.new hexagon(hexagonSide);
                    System.out.printf("Hexagon: Area = %.2f, Perimeter = %.2f%n", h.area(), h.perimeter());
                    break;

                default:
                    System.out.println("Unknown shape type!");
            }
        }

        scanner.close();
    }
}