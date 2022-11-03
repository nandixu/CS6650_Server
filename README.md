# CS6650_Server

This is the server of CS6650 Assignment 1.

Link to Client Repo: https://github.khoury.northeastern.edu/nandi/CS6650_Client.git

Link to RMQ Reiceiver: https://github.khoury.northeastern.edu/nandi/CS6650_RMQ_Receiver.git

## SkierServlet
This is a servlet that would do a brief process on the request sent by client, and generate response data. The current version supports doPost and doGet methods, a simple url validation method. Also, an init() method and send() method is added in Assignment2.

### init() method
This is a method that would create the ConnectionFactory and Connection to the remote RabbitMQ server. It will be initialized as the server started, and these ConnectionFactory and Connection will be used in send() method.

### doPost() method
This method will handle a POST request and send a message to a remote RabbitMQ. The method will first read the JSON body into a bufferWriter and print out the POST info. For each POST, it will call the send() method.

### send() method 
This method will create a channel, declare a queue, and publish a message to the remote RabbitMQ. 

Please ignore HelloWorldServlet which I used to practice :)
