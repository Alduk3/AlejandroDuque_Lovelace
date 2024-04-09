package controller;

import entity.Company;
import model.CompanyModel;

import javax.swing.*;
import java.util.List;

public class CompanyController {
    public static void getAll(){
        // Use model
        CompanyModel companyModel = new CompanyModel();

        // Create string list to company
        String companyList = "Companies: \n";

        for (Company iterator : companyModel.findAll()){
            companyList += iterator.toString();
        }

        JOptionPane.showMessageDialog(null, companyList);
    }

    public static Company getCompany(){
        // Use model
        CompanyModel companyModel = new CompanyModel();

        // Create list
        List<Company> companies = companyModel.findAll();

        Object option = JOptionPane.showInputDialog(
                null,
                "Choose an company: ",
                "Companies",
                JOptionPane.QUESTION_MESSAGE,
                null,
                companies.toArray(),
                companies.getFirst()
        );

        return (Company) option;
    }

    public static Company getById(int id_company){
        // Use model
        CompanyModel companyModel = new CompanyModel();
        Company company= null;
        try {
            company = companyModel.findById(id_company);
        } catch (Exception error){
            JOptionPane.showMessageDialog(null, "Company not found");
        }
        return company;
    }
}
