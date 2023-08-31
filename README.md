# Student Budgeting Application

## Managing finances with Java

This projects will allow students to keep a record of their income and expenses 
as they progress through the school year. It will be used by students who struggle to 
keep track of this on their own or ones who want an easy application to do so. This 
project is of interest to me since I want to better budget my spending and have friends who say the same. 
As a student, I want to responsibly track how much money I am applying to different categories of spending. 

**User Stories:**

Phase 0
- As a user, I want to be able to create a new expense and document it with other pre-existing expenses 
- As a user, I want to be able to see my total spending
- As a user, I want to be able to see how much money I have left
- As a user, I want to be able to add a date to an expense

Phase 1
- As a user, when I quit my application, I want to be given the option to save my income and expense reports 
- As a user, when I open my application, I want to be given the option to load my reports from file

Phase 4: Task 2

Here is an example of the events that occur when the program runs which displays when the 
window is exited:
- An income entry has been added.
- An expense entry has been added.
- A single entry has been removed

Phase 4: Task 3

It is evident that there are two sets of similar classes, ExpenseReportTab/IncomeReportTab and IncomeAdder/ExpenseAdder.
When looking at the code for these classes there is lots of similar code. ExpenseReportTab and IncomeReportTab share
similar functions as they display their respective income/expense report summaries. IncomeAdder and ExpenseAdder both 
add new single entries to their respective reports. Refactoring this code by utilizing better inheritance would 
decrease the code similarities and improve the design of the program. One of the main reasons this
was not originally done was because I was new to Java Swing when creating the application.