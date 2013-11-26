@run
Feature: Refresh 
	In order to keep the weather up to date
	As a user
	I want to refresh the page with a button

Scenario: Press Refresh button
	Given the website is loaded
	When I press the Refresh button
	Then the button should register a click and refresh