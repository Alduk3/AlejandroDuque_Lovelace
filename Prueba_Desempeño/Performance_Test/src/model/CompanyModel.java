package model;

import database.ConfigDB;
import entity.Company;
import entity.Vacant;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CompanyModel {

    public List<Company> findAll(){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // Create list to companies
        List<Company> companyList = new ArrayList<>();

        try {
            // SQL
            String sql = "SELECT * FROM company;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Create new company
                Company company = new Company();

                // Set value to company
                company.setId_company(resultSet.getInt("id_company"));
                company.setName(resultSet.getString("name"));
                company.setSector(resultSet.getString("sector"));
                company.setLocation(resultSet.getString("location"));
                company.setContact(resultSet.getString("contact"));

                // Add company to list
                companyList.add(company);
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return companyList;
    }

    public Company findById(int id_company){
        // Open connection
        Connection connection = ConfigDB.openConnection();

        // create vacant
        Company company = new Company();

        try {
            // SQL
            String sql = "SELECT * FROM company WHERE id_company = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // set value to ?
            preparedStatement.setInt(1, id_company);

            // Execute query and get response
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                // Set value to company
                company.setId_company(resultSet.getInt("id_company"));
                company.setName(resultSet.getString("name"));
                company.setSector(resultSet.getString("sector"));
                company.setLocation(resultSet.getString("location"));
                company.setContact(resultSet.getString("contact"));
            }

        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Error >> " + error.getMessage());
        } finally {
            ConfigDB.closeConnection();
        }
        return company;
    }
}
