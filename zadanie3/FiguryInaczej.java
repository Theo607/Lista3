import java.util.Scanner;
class Figure {
    public interface FirstInterface {
        public static void SetDimension() {
        }
        public static double Area() {
            return 0.0;
        }
        public static double Perimeter() {
            return 0.0;
        }
        public static String Name() {
            return "Figure";
        }
    }
    public interface SecondInterface {
        void SetDimension(double dimension1, double dimension2);
        double Area();
        double Perimeter();
        String Name();
    }   

    enum FirstClass implements FirstInterface {
        CIRCLE {
            private double radius = 0;

            @Override
            public void SetDimension(double r) {
                radius = r; 
            }

            @Override
            public double Area() {
                return Math.PI * Math.pow(radius, 2);
            }

            @Override
            public double Perimeter() {
                return 2 * Math.PI * radius;
            }

            @Override
            public String Name() {
                return "Circle";
            }
        },
        SQUARE {
            private double side = 0;

            @Override
            public void SetDimension(double s) {
                side = s; 
            }

            @Override
            public double Area() {
                return Math.pow(side, 2);
            }

            @Override
            public double Perimeter() {
                return 4 * side;
            }

            @Override
            public String Name() {
                return "Square";
            }
        },
        PENTAGON {
            private double side = 0;

            @Override
            public void SetDimension(double s) {
                side = s; 
            }

            @Override
            public double Area() {
                return (Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) / 4) * Math.pow(side, 2);
            }

            @Override
            public double Perimeter() {
                return 5 * side;
            }

            @Override
            public String Name() {
                return "Pentagon";
            }
        },
        HEXAGON {
            private double side = 0;

            @Override
            public void SetDimension(double s) {
                side = s; 
            }

            @Override
            public double Area() {
                return ((3 * Math.sqrt(3)) / 2) * Math.pow(side, 2);
            }

            @Override
            public double Perimeter() {
                return 6 * side;
            }

            @Override
            public String Name() {
                return "Hexagon";
            }
        };

        public abstract void SetDimension(double value);
        public abstract double Area();
        public abstract double Perimeter();
        public abstract String Name();
    }

    enum SecondClass implements SecondInterface {
       RECTANGLE {
            private double length = 0;
            private double width = 0;

            @Override
            public void SetDimension(double dimension1, double dimension2) {
                length = dimension1;
                width = dimension2;
            }

            @Override
            public double Area() {
                return length * width;
            }

            @Override
            public double Perimeter() {
                return 2 * (length + width);
            }

            @Override
            public String Name() {
                return "Rectangle";
            }
        },
        RHOMBUS {
            private double side = 0;
            private double angle = 0;

            @Override
            public void SetDimension(double dimension1, double dimension2) {
                side = dimension1;
                angle = dimension2;
            }

            @Override
            public double Area() {
                return side * side * Math.sin(Math.toRadians(angle));
            }

            @Override
            public double Perimeter() {
                return 4 * side;
            }

            @Override
            public String Name() {
                return "Rhombus";
            }
       },
    }

    public static void main(String[] Args) {

        FirstClass[] figures = new FirstClass[100];
        SecondClass[] figures2 = new SecondClass[100];
        int i = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter figure details. Type 'show' to see results.");

    while (true) {
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("show")) {
            System.out.println("Showing the answers.");
            break;
        }

        String[] parts = input.split(" ");
        String shapeType = parts[0];

        try {
            switch (shapeType) {
                case "o": // Circle
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid input for circle. Expected format: o <radius>");
                    }
                    double radius = Double.parseDouble(parts[1]);
                    figures[i] = FirstClass.CIRCLE;
                    ((FirstClass) figures[i]).SetDimension(radius);
                    i++;
                    break;

                case "c": // Square, Rectangle, or Rhombus
                    if (parts.length == 3) {
                        double side = Double.parseDouble(parts[1]);
                        double angle = Double.parseDouble(parts[2]);
                        if (angle == 90) {
                            figures[i] = FirstClass.SQUARE;
                            ((FirstClass) figures[i]).SetDimension(side);
                            i++;
                        } else {
                            figures2[i] = SecondClass.RHOMBUS;
                            ((SecondClass) figures2[i]).SetDimension(side, angle);
                            i++;
                        }
                    } else if (parts.length == 4 && parts[3].equals("90")) {
                        double length = Double.parseDouble(parts[1]);
                        double width = Double.parseDouble(parts[2]);
                        figures2[i] = SecondClass.RECTANGLE;
                        ((SecondClass) figures2[i]).SetDimension(length, width);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Invalid input for quadrangle. Expected format: c <side> <angle> for square or rhombus, or c <length> <width> 90 for rectangle.");
                    }
                    break;

                case "p": // Pentagon
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid input for pentagon. Expected format: p <side>");
                    }
                    double pentagonSide = Double.parseDouble(parts[1]);
                    figures[i] = FirstClass.PENTAGON;
                    ((FirstClass) figures[i]).SetDimension(pentagonSide);
                    i++;
                    break;

                case "s": // Hexagon
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Invalid input for hexagon. Expected format: s <side>");
                    }
                    double hexagonSide = Double.parseDouble(parts[1]);
                    figures[i] = FirstClass.HEXAGON;
                    ((FirstClass) figures[i]).SetDimension(hexagonSide);
                    i++;
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

    // Display results
    for (int j = 0; j < i; j++) {
        if (figures[j] instanceof FirstClass) {
            FirstClass figure = (FirstClass) figures[j];
            System.out.println(figure.Name() + " --- Area: " + figure.Area() + " --- Perimeter: " + figure.Perimeter());
        } else if (figures2[j] instanceof SecondClass) {
            SecondClass figure = (SecondClass) figures2[j];
            System.out.println(figure.Name() + " --- Area: " + figure.Area() + " --- Perimeter: " + figure.Perimeter());
        }
    }
    }
}