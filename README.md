# PetStoreAutomation
RestAssured Project Applying API's Automation Testing on Swagger (https://petstore.swagger.io)

## Overview

This project is a demonstration of using RestAssured for API testing in the context of a PetStore application. The PetStore API is a sample RESTful API that allows users to interact with a virtual pet store.

## Prerequisites

Make sure you have the following tools installed:

- Jdk 21
- Maven
- IntelliJ

## used tools 
- RestAssured Lib
- TestNG Framework
  
## E2E Scenario 
## for pets
- Create a new pet.
- Display an existing pet by id.
- Display all pets by their status.
- Update an existing pet.
- Update a pet with form data (name , status).
- Delete pet by Id
## for orders
- Create a new order.
- Get some order by id.
- Delete an order by id.
## for users
Dataprovider is applied to this part. getting the data from external file
- Create a new user. (used an external excel sheet to get userdata from)
- Get some user by its username. 
- Update some user by its username.
- Delete a user by its username.
