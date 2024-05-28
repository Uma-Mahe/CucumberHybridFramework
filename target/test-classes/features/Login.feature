Feature: Login functionality

Scenario Outline: Login with valid credentials
Given User navigates to login page
When User enters valid email address <username> in email address field
And User enters valid password <password> in password field
And User clicks on Login button
Then User loggedin successfully
Examples:
|username|password|
|amotooricap1@gmail.com|12345|
|amotooricap2@gmail.com|12345|
|amotooricap3@gmail.com|12345|

Scenario: Login with invalid credentials
Given User navigates to login page
When User enters invalid email address in email address field
And User enters invalid password "123456" in password field
And User clicks on Login button
Then User should get a warning message about credentials mismatch

Scenario: Login with valid email and invalid password
Given User navigates to login page
When User enters valid email address "uma6mahe@gmail.com" in email address field
And User enters invalid password "12345678" in password field
And User clicks on Login button
Then User should get a warning message about credentials mismatch

Scenario: Login with invalid email and valid password
Given User navigates to login page
When User enters invalid email address in email address field
And User enters valid password "12345" in password field
And User clicks on Login button
Then User should get a warning message about credentials mismatch

Scenario: Login without any credentials
Given User navigates to login page
When User dont enters email address in email address field
And User dont enters password in password field
And User clicks on Login button
Then User should get a warning message about credentials mismatch


 