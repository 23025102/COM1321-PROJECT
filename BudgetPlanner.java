
package budgetplanner;



import java.util.ArrayList;
import java.util.Scanner;

// Abstract class for all types of expenses
abstract class Expense {
     String category;
    double amount;

    public Expense(String category, double amount) {
        category = category;
        amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        amount = amount;
    }

    public abstract void displayExpense();
}

// Class for handling home loan-specific expenses
class HomeLoan extends Expense {
     double purchasePrice;
     double deposit;
     int interestRate;
     int monthsToRepay;
    double loanAmount;

    public HomeLoan(double purchasePrice, double deposit, double interestRate, int monthsToRepay) {
        super("Home Loan", 0);
       this.purchasePrice = purchasePrice;
       this.deposit = deposit;
       interestRate = interestRate;
       this.monthsToRepay = monthsToRepay;
       amount  = calculateMonthlyRepayment();
    }

        double calculateMonthlyRepayment() {
        double loanAmount = purchasePrice - deposit;
        double monthlyInterestRate = (interestRate / 100);
       
        return loanAmount * (Math.pow(1 + monthlyInterestRate, monthsToRepay));
    }

     
    public void displayExpense() {
        System.out.println("Home Loan Monthly Repayment: " + amount);
    }
}

// Class for handling rent expenses
class Rent extends Expense {
    public Rent(double rentAmount) {
        super("Rent", rentAmount);
        amount = rentAmount;
    }

    public void displayExpense() {
        System.out.println("Monthly Rent: " + amount);
    }
}

// Class for handling other simple expenses like groceries, etc.
class SimpleExpense extends Expense {
    public SimpleExpense(String category, double amount) {
        super(category, amount);
    }

   
    public void displayExpense() {
        System.out.println(category + ": " + amount);
    }
}

// Class for handling vehicle expenses
class VehicleExpense extends Expense {
    String modelAndMake;
    double purchasePrice;
    double totalDeposit;
    int interestRate;
    double insurancePremium;

    public VehicleExpense(String modelAndMake, double purchasePrice, double totalDeposit, double interestRate, double insurancePremium) {
        super("Vehicle", 0);
        this.modelAndMake = modelAndMake;
        this.purchasePrice = purchasePrice;
        this.totalDeposit = totalDeposit;
        interestRate = interestRate;
       this. insurancePremium = insurancePremium;
        amount = calculateMonthlyRepayment();
    }


    

     double calculateMonthlyRepayment() {
        double loanAmount = purchasePrice - totalDeposit ;
        double monthlyInterestRate = (interestRate / 100) ;
        return loanAmount * ( Math.pow(1 + monthlyInterestRate, 60)) + insurancePremium;
        
     
     }
    public void displayExpense() {
        System.out.println("Vehicle Monthly Repayment: " + amount);
        
    }
    
}

// Main class for managing the budget and expense details
public class BudgetPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input gross income and tax
        
        System.out.println("Enter your gross monthly income: ");
        double grossIncome = scanner.nextDouble();
        
        System.out.println("Enter estimated monthly tax(IN PERCENTAGE): ");
        int tax = scanner.nextInt();

        // Input basic monthly expenses
        
        System.out.println("Enter monthly groceries expense: ");
        double groceries = scanner.nextDouble();
        
        System.out.println("Enter monthly water and lights expense: ");
        double waterLights = scanner.nextDouble();
        
        System.out.println("Enter monthly travel expense: ");
        double travel = scanner.nextDouble();
        
        System.out.println("Enter monthly phone expense: ");
        double cellPhone = scanner.nextDouble();
        
        System.out.println("Enter any other monthly expenses: ");
        double other = scanner.nextDouble();

        // Create a ArrayList to store expenses
        
         ArrayList<String> expenses = new ArrayList<>();
        expenses.add("Groceries: " + groceries);
        expenses.add("Water and Lights: " + waterLights);
        expenses.add("Travel: " + travel);
        expenses.add("Cell Phone: " + cellPhone);
        expenses.add("Other: " + other);

        
        // Rent or Buy decision
        System.out.println("Do you want to rent or buy a property? (rent/buy): ");
        String decision = scanner.next();
        Expense homeExpense = null;
        switch (decision){
            
           case "rent" :
               
            System.out.println("Enter your monthly rent: ");
            double rentAmount = scanner.nextDouble();
            homeExpense = new Rent(rentAmount);
            
         break;   
            
           case "buy":
        
            System.out.println("Enter property purchase price: ");
            double purchasePrice = scanner.nextDouble();
            
            System.out.println("Enter total deposit: ");
            double deposit = scanner.nextDouble();
            
            System.out.println("Enter interest rate (IN PERCENTAGE): ");
            int interestRate = scanner.nextInt();
            
            System.out.println("Enter number of months to repay (between 240 and 360): ");
            int monthsToRepay = scanner.nextInt();
            
            homeExpense = new HomeLoan(purchasePrice,deposit,interestRate,monthsToRepay);
         break;   
            
            
           default:{
               System.out.println("Error!Please Enter rent or buy");
           }
        }
                
                


        // Vehicle purchase decision
        System.out.println("Do you want to buy a vehicle? (yes/no): ");
        String vehicleDecision = scanner.next();
         Expense vehicleExpense = null;
        
        switch (vehicleDecision) {
        
            case "yes":
            System.out.println("Enter vehicle model and make: ");
            String modelAndMake = scanner.next();
            
            System.out.println("Enter vehicle purchase price: ");
            double vehiclePurchasePrice = scanner.nextDouble();
            
            System.out.println("Enter total deposit: ");
            double vehicleDeposit = scanner.nextDouble();
            
            System.out.println("Enter interest rate (IN PERCENTAGE): ");
            int vehicleInterestRate = scanner.nextInt();
            
            System.out.println("Enter estimated insurance premium: ");
            double insurancePremium = scanner.nextDouble();
            
            vehicleExpense = new VehicleExpense(modelAndMake, vehiclePurchasePrice, vehicleDeposit, vehicleInterestRate, insurancePremium);
       break;
             
             case"no":
                 
                 break;
              
             default:{
                 System.out.println("Error! Please Enter yes or no");
             }
              }
// Sort expenses in descending order
expenses.sort((a, b) -> Double.compare(Double.parseDouble(b.split(": ")[1]), Double.parseDouble(a.split(": ")[1])));

    
// Calculate total expenses
double totalExpenses = 0.0;
for (String expense : expenses) {
    String[] parts = expense.split(": ");
    double amount = Double.parseDouble(parts[1]);
    totalExpenses += amount;
    System.out.println("Expense: " + parts[0] + " R " + amount); 
}

// displaying home loan monthly repayment
 if (homeExpense != null){
     homeExpense.displayExpense();
}   

// displaying vehicle monthly repayment
if (vehicleExpense != null) {
    vehicleExpense.displayExpense();
}

// Calculate remaining amount and diplaying total expenses
System.out.println("Total Expense:" + totalExpenses);
double remainingAmount = grossIncome - totalExpenses;
System.out.println("Gross Income: " + grossIncome);
System.out.println("Remaining Amount: " + remainingAmount);


// Check if total expenses exceed 75% of gross income
if (totalExpenses > grossIncome * 0.75) {
    System.out.println("Warning: Total expenses exceed 75% of gross income!");

        }
    }
        }



    
    



