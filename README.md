# saucedemo

Normal framework structure that i have used to complete the assignment. I was more comfortable in Java with cucumber, hence using cucumber feature implemented this assigment.

Completed the assignment as per the steps provided 

Getting Started: For easiest way to getting started, extract this project and open it from Eclipse or any IDE of your choice which supports Java

Then Do a run on test in : testRunner class using Junit Test, Mean to say run as Junit Test

Should be able to see four scenarios running

1. Standard_user login and adding 3 random items and validate if 3 items present on the cart
2. Problem_user login and adding 3 random items and validate if 3 items present on the cart
3. performance_glitch_user login and adding 3 random items and validate if 3 items present on the cart
4. Locked_user will fail to login as user is locked out.

For first 3 scenarios I am passing username and password as parameters and for locked user i have hardcoded on step definition as it will fail to login, you can change the username and password from feature file

Note: used implicit wait for 20 seconds on chromedriver, so to find any locator on web page it waits for 20 seconds 
