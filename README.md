# APU-Gym-Ccentre-Management-System
Year 2 Java Assignment

# Introduction

In this module, we have studied to use Java, which is an object-oriented programming 
language and software developing platform that currently being used on a huge number of 
devices, from the range of personal notebook computers to data centres, even super 
computers. It is extremely fast, reliable, and portable. This makes Java a solid option for 
enterprise application since the program can run on any devices that have installed Java 
Runtime once it is compiled correctly. (Education, 2019)

Besides that, we are required to implement Object-Oriented Programming (OOPs) 
concepts while developing with Java, which is a common style of programming which design 
the code that operate like the pattern of objects in the real world. (LLC, n.d.) To do that, we 
will create several classes and objects that bind with data as well as methods using OOPs like
inheritance and polymorphism, which will be discussed later in this report. (Prabhu, 2022)
For this coursework, we will build a system named APU Gym Centre Management 

System (AGC). This system aims to serve the managers and trainers of APU Gym Centre.
Managers can use the system to handle end user registration as well as assigning training 
sessions to customers and trainers. In the other hand, trainers can use the system to check the 
training sessions assigned to them, provide feedback after finishing each session, and collect 
payment. We have also added some extra features to make the system more reliable and 
friendly to users.

# Design

![image](https://user-images.githubusercontent.com/80668891/192737229-b90eabb4-0e9e-4330-81f4-d369e7b162bd.png)
![image](https://user-images.githubusercontent.com/80668891/192737248-d8d3d278-611a-48f0-b019-d2ffa0ac3ee7.png)

# Preview

![image](https://user-images.githubusercontent.com/80668891/192737359-588b4513-2763-49ba-b483-d77e7b682a19.png)
![image](https://user-images.githubusercontent.com/80668891/192737337-2eb4b499-b0fe-4d44-b19d-b69d5e5971a0.png)
![image](https://user-images.githubusercontent.com/80668891/192737406-36612227-69ae-43c8-8509-cd5b674bff70.png)
![image](https://user-images.githubusercontent.com/80668891/192737437-39d916c8-dd35-4a5e-ae63-001a276660a3.png)
![image](https://user-images.githubusercontent.com/80668891/192737460-6ecb3edc-ad52-49dc-87cc-fdc655522913.png)
![image](https://user-images.githubusercontent.com/80668891/192737491-794b3c7c-53b5-44b8-ad5a-8ed4b528f2c5.png)
![image](https://user-images.githubusercontent.com/80668891/192737504-143d7a03-2877-419c-902c-2988a1018a51.png)


# Additional Functions

1. Auto Email Notification
We have designed an additional functionality called auto email notification, which can be 
enabled or disabled on the advanced administration page. Once it is enabled, the system will 
send emails to both trainer and customer informing the detail of session when a session is 
booked

2. Auto Monthly Withdrawal for Trainer
Next, we have designed another additional functionality called auto monthly withdrawal for 
trainer, which can also be enabled or disabled on the advanced administration page. When 
this function is enabled, the system will automatically withdraw the balance in all trainer 
accounts to their associated bank on every 15th of the month, so trainer don’t need to 
withdraw manually.

3. Peak Hour Mode
Lastly, we have made another additional functionality, named peak hour mode, which can be 
managed on the advanced administration page. This function is meant to be enabled when 
there are too many sessions are being booked and causing insufficient of session slot.
When this function is enabled, the system will increase the price of session from RM25/hour 
to RM40/hour; and revert to RM25/hour when this function is disabled.
