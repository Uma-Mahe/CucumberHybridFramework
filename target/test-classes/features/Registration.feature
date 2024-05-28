Feature: Registration functionality

Scenario: User creates an account only with mandatory fields
Given user navigates to Register count Page
When User enters the details into the below fields
|FirstName | Uma	 |
|LastName | Uma |
|Telephone | 123456789 |
|Password | 12345 |
|PasswordConfirm | 12345 |
And User select privacy policy
And User Clicks on continue button
Then User account should get created successfully

Scenario: User creates an account with all fields
Given user navigates to Register count Page
When User enters the details into the below fields
|FirstName | Uma	 |
|LastName | Uma |
|Telephone | 123456789 |
|Password | 12345 |
|PasswordConfirm | 12345 |
And User selects for newsletter
And User select privacy policy
And User Clicks on continue button
Then User account should get created successfully

Scenario: User creates a duplicate account
Given user navigates to Register count Page
When User enters the details into the below fields with duplicate email
|FirstName | Uma	 |
|LastName | Uma |
|Email | uma6mahe@gmail.com |
|Telephone | 123456789 |
|Password | 12345 |
|PasswordConfirm | 12345 |
And User selects for newsletter
And User select privacy policy
And User Clicks on continue button
Then User should get proper warning about duplicate email address

Scenario: User creates an account without any details
Given user navigates to Register count Page
When User dont enters the details into the fields
And User Clicks on continue button
Then User account should get warning message for every mandatory fields
