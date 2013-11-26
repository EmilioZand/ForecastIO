@run
Feature: Expandables 

Scenario: Expanding elements of the website
	Given the website is loaded with a location
	When I expand elements of the website
	Then more information should be visible