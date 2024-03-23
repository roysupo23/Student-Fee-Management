The project is created as a part of the task provided by interviewer. The details and requirements of the project are present in the pdf file inside resource folder.

As per the requirement the project should perform 3 operations which are:
1. Add students
2. Collect fees from students
3. View reciepts of the fees

Ther are total 4 APis present which performs the below taks
1. Add students into the system
2. Collects fees deposited by the payee on behalf of the student, if student does not submit their own fees
3. View the details to populate the template of the reciept provided part of the requirement
4. Download a pdf of the reciept for visualizations

Below are the validations added
For Student Onboarding:
1. Mobile number provided during onboarding of student should not be present in the system with same student name
2. All the fields are manadatory for onboarding.
3. Mobile number can contain only numbers and ( ) - + as special characters
4. Almost all international numbers are covered as part of validation

For Fee collection
1. The student id and student name should match.
2. The Student id should be available in the system

For Reciept download/view
1. Reference number should be valid
