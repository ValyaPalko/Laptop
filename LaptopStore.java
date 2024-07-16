import java.util.*;
import java.util.stream.Collectors;

public class LaptopStore {
    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>(Arrays.asList(
                new Laptop("Dell", 8, 256, "Windows 10", "Black"),
                new Laptop("HP", 16, 512, "Windows 10", "Silver"),
                new Laptop("Apple", 8, 256, "macOS", "Gray"),
                new Laptop("Asus", 4, 128, "Windows 10", "Blue"),
                new Laptop("Lenovo", 8, 256, "Windows 10", "Black")
        ));

        Map<String, String> criteria = getFilterCriteria();
        Set<Laptop> filteredLaptops = filterLaptops(laptops, criteria);

        System.out.println("Filtered laptops:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }
    }

    private static Map<String, String> getFilterCriteria() {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> criteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (criterion) {
            case 1:
                System.out.print("Введите минимальное значение ОЗУ (в ГБ): ");
                criteria.put("ram", scanner.nextLine());
                break;
            case 2:
                System.out.print("Введите минимальное значение Объема ЖД (в ГБ): ");
                criteria.put("storage", scanner.nextLine());
                break;
            case 3:
                System.out.print("Введите Операционную систему: ");
                criteria.put("os", scanner.nextLine());
                break;
            case 4:
                System.out.print("Введите Цвет: ");
                criteria.put("color", scanner.nextLine());
                break;
            default:
                System.out.println("Неверный критерий");
                break;
        }

        return criteria;
    }

    private static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> criteria) {
        return laptops.stream().filter(laptop -> {
            boolean matches = true;

            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < Integer.parseInt(entry.getValue())) {
                            matches = false;
                        }
                        break;
                    case "storage":
                        if (laptop.getStorage() < Integer.parseInt(entry.getValue())) {
                            matches = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase(entry.getValue())) {
                            matches = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase(entry.getValue())) {
                            matches = false;
                        }
                        break;
                }
            }

            return matches;
        }).collect(Collectors.toSet());
    }
}

class Laptop {
    private String brand;
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color;

    public Laptop(String brand, int ram, int storage, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return ram == laptop.ram &&
                storage == laptop.storage &&
                Objects.equals(brand, laptop.brand) &&
                Objects.equals(os, laptop.os) &&
                Objects.equals(color, laptop.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, ram, storage, os, color);
    }
}

