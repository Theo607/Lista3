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
        FirstClass.CIRCLE.SetDimension(5.0);
        System.out.println(FirstClass.CIRCLE.Name() + " Area: " + FirstClass.CIRCLE.Area());
        System.out.println(FirstClass.CIRCLE.Name() + " Perimeter: " + FirstClass.CIRCLE.Perimeter());

        FirstClass.SQUARE.SetDimension(4);
        System.out.println(FirstClass.SQUARE.Name() + " Area: " + FirstClass.SQUARE.Area());
        System.out.println(FirstClass.SQUARE.Name() + " Perimeter: " + FirstClass.SQUARE.Perimeter());

        SecondClass.RECTANGLE.SetDimension(4.0, 5.0);
        System.out.println(SecondClass.RECTANGLE.Name() + " Area: " + SecondClass.RECTANGLE.Area());
        System.out.println(SecondClass.RECTANGLE.Name() + " Perimeter: " + SecondClass.RECTANGLE.Perimeter());

        SecondClass.RHOMBUS.SetDimension(4, 5);
        System.out.println(SecondClass.RHOMBUS.Name() + " Area: " + SecondClass.RHOMBUS.Area());
        System.out.println(SecondClass.RHOMBUS.Name() + " Perimeter: " + SecondClass.RHOMBUS.Perimeter());
    }
}