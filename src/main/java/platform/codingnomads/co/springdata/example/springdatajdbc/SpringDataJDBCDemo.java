package platform.codingnomads.co.springdata.example.springdatajdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringDataJDBCDemo implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJDBCDemo.class);
    }

    @Override
    public void run(String... strings) {

        try {
            //create employee table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE employees (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "first_name VARCHAR(255) NOT NULL,last_name  VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            //nothing
        }
        try {
            //create Chief table using the JdbcTemplate method "execute"
            jdbcTemplate.execute("CREATE TABLE chief (id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    "block VARCHAR(255) NOT NULL);");
        } catch (Exception e) {
            //nothing
        }

        //create a list of first and last names
        List<Object[]> splitUpNames = Stream.of("Java Ninja", "Spring Guru", "Java Guru", "Spring Ninja")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());
        //create a list of random words
        List<Object[]> randomWords = Stream.of("joint", "game", "tile", "fog", "foggy")
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        //for each first & last name pair insert an Employee into the database
        for (Object[] name : splitUpNames) {
            jdbcTemplate.execute(String.format("INSERT INTO employees(first_name, last_name) VALUES ('%s','%s')", name[0], name[1]));
        }
        //for each random word insert a chief into the database
        for (Object[] word : randomWords) {
            jdbcTemplate.execute(String.format("INSERT INTO chief(block) VALUES ('%s')", word[0]));
        }

        //query the database for Employees with first name Java
        jdbcTemplate.query(
                        "SELECT id, first_name, last_name FROM employees WHERE first_name = 'Java'",
                        (rs, rowNum) -> new Employee(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
                )
                //print each found employee to the console
                .forEach(employee -> System.out.println(employee.toString()));
        //query the database for chief ordered ascending
        jdbcTemplate.query(
                        "SELECT id, block FROM chief ORDER BY block",
                        (rs, rowNum) -> new Chief(rs.getLong("id"), rs.getString("block"))
                )
                //print each found chief to the console
                .forEach(chief -> System.out.println(chief.toString()));

        //truncate the table
        jdbcTemplate.execute("TRUNCATE TABLE employees;");
        jdbcTemplate.execute("TRUNCATE TABLE chief;");
        //delete the table
        jdbcTemplate.execute("DROP TABLE employees");
        jdbcTemplate.execute("DROP TABLE chief");
    }
}
