@run
Feature: TimeMachine 
	In order to use the TimeMachine properly
	As a user
	I want to only have correct inputs accepted

Scenario Outline: Press Refresh button
	Given the website is loaded and TimeMachine is clicked
	When I enter <text> into the date input box
	Then I should see that the TimeMachine <result> worked
	
Examples:
		| text			| result				|
		| blank			| unsuccessfully		|
		| done			| unsuccessfully		|
		| 13/13/13		| unsuccessfully		|
		| 1/1/01		| successfully			|