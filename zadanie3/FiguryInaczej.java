import java.util.Scanner;

public class FiguryInaczej {
    public class Figures {
        interface FirstInterface {
            public static void SetDimension() {}
            public static double Surface() {}
            public static double Perimeter() {}
            public static String Name() {}
        }
        interface SecondInterface {
            public static void SetDimension() {}
            public static double Surface() {}
            public static double Perimeter() {}
            public static String Name() {}
        }
        enum FirstClass implements FirstInterface {
            
            CIRCLE {
                public double radius;
                public  void SetDimension(double dimension) {
                    radius = dimension;
                }
                public  double Surface() {
                    return Math.PI * radius * radius;
                }
                public  double Perimeter() {
                    return Math.PI * 2.0 * radius;
                }
                public  String Name() {
                    return "Circle";
                }
            },

            SQUARE {
                public double lenght;
                public void SetDimension(double dimension) {
                    lenght = dimension;
                }
                public double Surface() {
                    return lenght * lenght;
                }
                public double Perimeter() {
                    return 4.0 * lenght;
                }
                public String Name() {
                    return "Square";
                }
            },

            PENTAGON {
                private double sideLength;
    
                public void SetDimension(double dimension) {
                    sideLength = dimension;
                }

                public double Surface() {
                    return 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * sideLength * sideLength;
                }
    
                public double Perimeter() {
                    return 5.0 * sideLength;
                }

                public String Name() {
                    return "Pentagon";
                }
            },
    
            HEXAGON {
                private double sideLength;

                public void SetDimension(double dimension) {
                    sideLength = dimension;
                }

                public double Surface() {
                    return 1.5 * Math.sqrt(3) * sideLength * sideLength;
                }

                public double Perimeter() {
                    return 6.0 * sideLength;
                }

                public String Name() {
                    return "Hexagon";
                }
            }
        }
        enum SecondClass implements FirstInterface {
            RECTANGLE {
                private double length;
                private double width;

                public void SetDimension(double dimension1, double dimension2) {
                    length = dimension1;
                    width = dimension2;
                }

                public double Surface() {
                    return length * width;
                }

                public double Perimeter() {
                    return 2.0 * (length + width);
                }

                public String Name() {
                    return "Rectangle";
                }
            },
    
            RHOMBUS {
                private double sideLength;
                private double angle;

                public void SetDimension(double dimension1, double dimension2) {
                    sideLength = dimension1;
                    angle = dimension2;
                }

                public double Surface() {
                    return sideLength * sideLength * Math.sin(Math.toRadians(angle));
                }

                public double Perimeter() {
                    return 4.0 * sideLength;
                }

                public String Name() {
                    return "Rhombus";
                }
            };
        }
    }

    public static void main(String[] args) {
        FirstClass[] figures1 = new FirstClass[100];
        SecondClass[] figures2 = new SecondClass[100];
        int i1 = 0; // Index for figures1
        int i2 = 0; // Index for figures2

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
                    case "o":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for circle. Expected format: o <radius>");
                        }
                        double radius = Double.parseDouble(parts[1]);
                        figures1[i1] = FirstClass.CIRCLE;
                        figures1[i1].SetDimension(radius);
                        i1++;
                        break;

                    case "c":
                        if (parts.length == 3) {
                            double side = Double.parseDouble(parts[1]);
                            double angle = Double.parseDouble(parts[2]);
                            if (angle == 90) {
                                figures1[i1] = FirstClass.SQUARE;
                                figures1[i1].SetDimension(side);
                                i1++;
                            } else {
                                figures2[i2] = SecondClass.RHOMBUS;
                                figures2[i2].SetDimension(side, angle);
                                i2++;
                            }
                        } else if (parts.length == 4 && parts[3].equals("90")) {
                            double a = Double.parseDouble(parts[1]);
                            double b = Double.parseDouble(parts[2]);
                            figures2[i2] = SecondClass.RECTANGLE;
                            figures2[i2].SetDimension(a, b);
                            i2++;
                        } else {
                            throw new IllegalArgumentException("Invalid input for quadrangle. Expected format: c <side> <angle> for square or rhombus, or c <a> <b> 90 for rectangle.");
                        }
                        break;

                    case "p":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for pentagon. Expected format: p <side>");
                        }
                        double pentagonSide = Double.parseDouble(parts[1]);
                        figures1[i1] = FirstClass.PENTAGON;
                        figures1[i1].SetDimension(pentagonSide);
                        i1++;
                        break;

                    case "s":
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Invalid input for hexagon. Expected format: s <side>");
                        }
                        double hexagonSide = Double.parseDouble(parts[1]);
                        figures1[i1] = FirstClass.HEXAGON;
                        figures1[i1].SetDimension(hexagonSide);
                        i1++;
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

        System.out.println("FirstClass figures:");
        for (int j = 0; j < i1; j++) {
            System.out.println(figures1[j].Name() + " --- Surface: " + figures1[j].Surface() + " --- Perimeter: " + figures1[j].Perimeter());
        }

        System.out.println("SecondClass figures:");
        for (int j = 0; j < i2; j++) {
            System.out.println(figures2[j].Name() + " --- Surface: " + figures2[j].Surface() + " --- Perimeter: " + figures2[j].Perimeter());
        }
    }
}
