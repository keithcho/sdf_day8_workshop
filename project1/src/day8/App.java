package day8;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        String dir;
        String fileName;
        String outputPath = "data/sample.txt";

        if (args.length > 0) {
            String[] arguments = args[0].split("/");
            dir = arguments[0];
            fileName = arguments[1];
            outputPath = dir + File.pathSeparator + fileName;
        }

        File file = new File(outputPath);


        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        List<Product> productList = new ArrayList<>();
        LocalDate ldCreated = LocalDate.of(2024,10,21);
        Date createDt = Date.from(ldCreated.atStartOfDay(ZoneId.systemDefault()).toInstant());

        productList.add(new Product(1L, "Mouse", "Clicking on the screen",
        "Computer", 99.0f, createDt));
        productList.add(new Product(2L, "Keyboard", "Device that allows alphanumeric inputs",
        "Computer", 235.5f, createDt));
        productList.add(new Product(3L, "15.6 inch Monitor", "Extended display panel",
        "Computer", 157.5f, createDt));
        productList.add(new Product(4L, "Huawei Pura 70 Ultra", "Huawei phone",
        "Mobile", 900f, createDt));
        productList.add(new Product(5L, "Huawei Mate 50 Pro", "Huawei phone",
        "Mobile", 1200f, createDt));
        productList.add(new Product(6L, "iPhone 16 Pro", "iPhone",
        "Mobile", 2000f, createDt));
        productList.add(new Product(7L, "iPhone 14 Pro", "iPhone",
        "Mobile", 1800f, createDt));

        List<Product> filteredProducts = new ArrayList<>();

        filteredProducts = productList.stream()
            .filter(p -> p.getProdCategory().equals("Mobile") && p.getPrice() > 1500.0f)
            .collect(Collectors.toList());

            
        Comparator<Product> comparator = Comparator.comparing(p -> p.getId());
        productList.sort(comparator.reversed());
        productList.forEach(System.out::println);
        
        try {
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                bw.append(iterator.next().toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Using Comparator to perform sorting
/*         Comparator<Product> comparator = Comparator.comparing(p -> p.getProdName());
        productList.sort(comparator);
        productList.forEach(System.out::println);

        System.out.println();

        productList.sort(comparator.reversed());
        productList.forEach(System.out::println); */
    }
}