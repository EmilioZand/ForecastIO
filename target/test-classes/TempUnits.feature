@run
Feature: TempUnits
	In order to accommodate for different systems
	As a user
	I want to be able to switch between Fahrenheit and Celsius

Scenario: Switch to Celsius
	Given the website is loaded and in Fahrenheit
	When I press the Celsius button
	Then the button should register a click and switch the temperature to Celsius