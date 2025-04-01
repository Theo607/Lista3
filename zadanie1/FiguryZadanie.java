import java.util.Scanner;

public class FiguryZadanie {
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

        @Override
        public double area() {
            return a * b; // Area of a rectangle is length * width
        }
    }

    class square extends quadrangle {
        square(int a) {
            super(a, a, a, a, 90);
        }

        @Override
        public double area() {
            return a * a; // Area of a square is side * side
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
        System.out.println("Enter shape details (e.g., c 8 8 4 4 90). Type 'exit' to quit.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program.");
                break;
            }

            String[] parts = input.split(" ");
            String shapeType = parts[0];
            FiguryZadanie f = new FiguryZadanie();

            try {
                switch (shapeType) {
                    case "o": // Circle
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for circle. Expected format: o <radius>");
                        }
                        int radius = Integer.parseInt(parts[1]);
                        circle c = f.new circle(radius);
                        System.out.printf("Circle: Area = %.2f, Perimeter = %.2f%n", c.area(), c.perimeter());
                        break;

                    case "c": // Quadrangle
                        if (parts.length == 3) { // Square
                            int side = Integer.parseInt(parts[1]);
                            square s = f.new square(side);
                            System.out.printf("Square: Area = %.2f, Perimeter = %.2f%n", s.area(), s.perimeter());
                        } else if (parts.length == 4 && parts[3].equals("90")) { // Rectangle
                            int a = Integer.parseInt(parts[1]);
                            int b = Integer.parseInt(parts[2]);
                            rectangle r = f.new rectangle(a, b);
                            System.out.printf("Rectangle: Area = %.2f, Perimeter = %.2f%n", r.area(), r.perimeter());
                        } else if (parts.length == 6) { // General quadrangle
                            int a = Integer.parseInt(parts[1]);
                            int b = Integer.parseInt(parts[2]);
                            int cSide = Integer.parseInt(parts[3]);
                            int d = Integer.parseInt(parts[4]);
                            double angle = Double.parseDouble(parts[5]);
                            quadrangle q = f.new quadrangle(a, b, cSide, d, angle);
                            System.out.printf("Quadrangle: Area = %.2f, Perimeter = %.2f%n", q.area(), q.perimeter());
                        } else {
                            throw new IllegalArgumentException("Invalid input for quadrangle. Expected format: c <a> <b> <c> <d> <angle>, c <side> <angle> for square, or c <a> <b> 90 for rectangle.");
                        }
                        break;

                    case "p": // Pentagon
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for pentagon. Expected format: p <side>");
                        }
                        int pentagonSide = Integer.parseInt(parts[1]);
                        pentagon p = f.new pentagon(pentagonSide);
                        System.out.printf("Pentagon: Area = %.2f, Perimeter = %.2f%n", p.area(), p.perimeter());
                        break;

                    case "s": // Hexagon
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for hexagon. Expected format: s <side>");
                        }
                        int hexagonSide = Integer.parseInt(parts[1]);
                        hexagon h = f.new hexagon(hexagonSide);
                        System.out.printf("Hexagon: Area = %.2f, Perimeter = %.2f%n", h.area(), h.perimeter());
                        break;

                    default:
                        System.out.println("Unknown shape type!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter valid integers or decimals.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}