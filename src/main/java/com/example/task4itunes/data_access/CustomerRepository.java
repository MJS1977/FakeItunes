package com.example.task4itunes.data_access;

import com.example.task4itunes.models.Customer;
import com.example.task4itunes.models.CustomerCount;
import com.example.task4itunes.models.CustomerSpend;
import com.example.task4itunes.logging.LogToConsole;
import com.example.task4itunes.models.GetRandomArtist;
import com.example.task4itunes.models.GetRandomGenre;
import com.example.task4itunes.models.GetRandomSong;
import com.example.task4itunes.models.SearchResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerRepository {

    private LogToConsole logger = new LogToConsole();

    // Setting up the connection object we need.
    private String URL = ConnectionHelper.CONNECTION_URL;
    private Connection conn = null;

    public ArrayList<Customer> getAllCustomers(){ //returns all the customers from the database
        ArrayList<Customer> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT customerId,firstName,lastName,country,postalCode,phone,email FROM customer");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new Customer(
                                resultSet.getString("customerId"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("country"),
                                resultSet.getString("postalCode"),
                                resultSet.getString("phone"),
                                resultSet.getString("email")
                        ));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return customers;
    }

    public Boolean addNewCustomer(Customer customer){ //add a new customer
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO customer(customerId,firstName,lastName,country,postalCode,phone,email) VALUES(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,customer.getCustomerId());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());
            preparedStatement.setString(4,customer.getCountry());
            preparedStatement.setString(5,customer.getPostalCode());
            preparedStatement.setString(6,customer.getPhone());
            preparedStatement.setString(7,customer.getEmail());

            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Add customer successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return success;
    }

    public Boolean updateCustomer(Customer customer){ //updates existing customer
        Boolean success = false;
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE customer SET customerId = ?, firstName = ?, lastName = ?, country = ? WHERE customerId=?");
            preparedStatement.setString(1,customer.getCustomerId());
            preparedStatement.setString(2,customer.getFirstName());
            preparedStatement.setString(3,customer.getLastName());
            preparedStatement.setString(4,customer.getCountry());
            preparedStatement.setString(5,customer.getCustomerId());

            // Execute Query
            int result = preparedStatement.executeUpdate();
            success = (result != 0);
            logger.log("Update customer successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return success;
    }

    public ArrayList<CustomerCount> getCustomersByCountry(){ //number of customers in each country
        ArrayList<CustomerCount> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT country, COUNT(*) AS customersPerCountry FROM customer GROUP BY  country ORDER BY COUNT(*) DESC ");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new CustomerCount(
                                resultSet.getString("country"),
                                resultSet.getString("customersPerCountry")
                        ));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return customers;
    }

    public ArrayList<CustomerSpend> getHighestSpender(){ // finds the highest spending customers
        ArrayList<CustomerSpend> customers = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT c.customerId, c.firstname, c.lastname,  Count(total) as total FROM Customer c INNER JOIN  Invoice i ON i.customerId = c.customerId GROUP BY total ORDER BY COUNT(total) DESC");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customers.add(
                        new CustomerSpend(
                                resultSet.getString("customerId"),
                                resultSet.getString("firstName"),
                                resultSet.getString("lastName"),
                                resultSet.getString("total")
                        ));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return customers;
    }


    public ArrayList<GetRandomArtist> getRandomArtist(){ //select 5 random artists
        ArrayList<GetRandomArtist> randomArtist = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT [Name] FROM artist ORDER  BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomArtist.add(
                        new GetRandomArtist(
                                resultSet.getString("Name")));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return randomArtist;
    }

    public ArrayList<GetRandomGenre> getRandomGenre(){ //select 5 random genres
        ArrayList<GetRandomGenre> randomGenre = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT [Name] FROM genre ORDER  BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomGenre.add(
                        new GetRandomGenre(
                                resultSet.getString("name")));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return randomGenre;
    }

    public ArrayList<GetRandomSong> getRandomSong(){  //select 5 random songs
        ArrayList<GetRandomSong> randomSong = new ArrayList<>();
        try{
            // Connect to DB
            conn = DriverManager.getConnection(URL);
            logger.log("Connection to SQLite has been established.");

            // Make SQL query
            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT [Name] FROM track ORDER  BY RANDOM() LIMIT 5");
            // Execute Query
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                randomSong.add(
                        new GetRandomSong(
                                resultSet.getString("name")));
            }
            logger.log("Select all customers successful");
        }
        catch (Exception exception){
            logger.log(exception.toString());
        }
        finally {
            try {
                conn.close();
            }
            catch (Exception exception){
                logger.log(exception.toString());
            }
        }
        return randomSong;
    }

    public SearchResult getSongInfo(String name){ //Not functional, just testing things...
        SearchResult result = null;
        // ---
        try{
            // connect
            conn = DriverManager.getConnection(URL);
            PreparedStatement prep =
                    conn.prepareStatement("SELECT Name, AlbumId, Composer " +
                            "FROM Track WHERE name =?");
            prep.setString(1,name);
            ResultSet set = prep.executeQuery();
            while(set.next()){
                result = new SearchResult(
                        set.getString("AlbumId"),
                        set.getString("Composer")
                );
            }
            System.out.println("Get specific went well!");

        }catch(Exception exception){
            System.out.println(exception.toString());
        }
        finally {
            try{
                conn.close();
            } catch (Exception exception){
                System.out.println(exception.toString());
            }
        }
        return result;
    }
}
