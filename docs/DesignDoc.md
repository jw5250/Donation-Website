---
geometry: margin=1in
---
# PROJECT Design Documentation
​
> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._
​
## Team Information
* Team name: placeholder
* Team members
  * Justin Wu
  * Daniel Arcega
  * Ricky Yang
  * John Li
​
## Executive Summary
​
This is a summary of the project.
This is a website designed for ArtsRoc, an organization which provides music and lego programs for young kids. It takes in donations from others via items which denote where the money spent will go, shows everybody what events the organization will hold in the future, and provides donation rewards for people who give enough. It also allows for members of the organization, given some permissions, to directly modify the events, the list of stuff people can buy from, and the rewards for donating some amount of money.
​
### Purpose
>  _**[Sprint 2 & 4]** Provide a very brief statement about the project and the most
> important user group and user goals._

This website is for ArtsRoc to help fund its endeavors through outside donations. Users should be able to pick and choose what programs they want to fund and receive rewards for their actions. U fund managers should be able to edit the data the user interacts with to best suit the organization's needs.
​
### Glossary and Acronyms
> _**[Sprint 2 & 4]** Provide a table of terms and acronyms._
​
| Term | Definition |
|------|------------|
| SPA | Single Page |
​
​
## Requirements
​
This section describes the features of the application.
​
> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._
​
### Definition of MVP
> _**[Sprint 2 & 4]** Provide a simple description of the Minimum Viable Product._

The ArtsRoc website that allows people to donate to ArtsRoc's various programs (or/and aspects of said programs). It also allows the people responsible for managing the funds to change what the donors can buy, given the organization wants more funding towards a different set of things than what's there already.
### MVP Features
>  _**[Sprint 4]** Provide a list of top-level Epics and/or Stories of the MVP._
​
### Enhancements
> _**[Sprint 4]** Describe what enhancements you have implemented for the project._
​
​
## Application Domain
​
This section describes the application domain.
​
![Domain Model](DomainModel.jpg)
​
As of now, the helper is a donor who has a set of items they can purchase in order to help ArtsRoc fund its programs (or aspects of them), and in turn, could get a reward given they’ve donated enough.
The checkout manager helps the user manage the set of orders they want to purchase alongside providing a way for them to do such.
​The donation system currently notifies users if they have a reward they can get, given some amount of points they earned by buying Needs. They can get each individual reward. The Ufund manager can edit, delet, and create whatever reward they think is the most attractive to helpers.
The event system currently allows helpers to see current events the organization will host in the future. The Ufund manager can edit, delete, and create what events the user can see.​

## Architecture and Design
​
This section describes the application architecture.
​
### Summary
​
The following Tiers/Layers model shows a high-level view of the webapp's architecture. 
**NOTE**: detailed diagrams are required in later sections of this document. (_When requested, replace this diagram with your **own** rendition and representations of sample classes of your system_.) 
​
![The Tiers & Layers of the Architecture](Tiers-and-Layers-Diagram-Spr3.drawio.png)
​
The web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 
​
The Model stores the application data objects including any functionality to provide persistance. 
​
The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.
​
Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.
​
​
### Overview of User Interface
​
This section describes the web interface flow; this is how the user views and interacts with the web application.
​
> _Provide a summary of the application's user interface.  Describe, from the user's perspective, the flow of the pages in the web application._
​
​
### View Tier
> _**[Sprint 4]** Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._
​
> _**[Sprint 4]** You must  provide at least **2 sequence diagrams** as is relevant to a particular aspects 
> of the design that you are describing.  (**For example**, in a shopping experience application you might create a 
> sequence diagram of a customer searching for an item and adding to their cart.)
> As these can span multiple tiers, be sure to include an relevant HTTP requests from the client-side to the server-side 
> to help illustrate the end-to-end flow._
​
> _**[Sprint 4]** To adequately show your system, you will need to present the **class diagrams** where relevant in your design. Some additional tips:_
 >* _Class diagrams only apply to the **ViewModel** and **Model** Tier_
>* _A single class diagram of the entire system will not be effective. You may start with one, but will be need to break it down into smaller sections to account for requirements of each of the Tier static models below._
 >* _Correct labeling of relationships with proper notation for the relationship type, multiplicities, and navigation information will be important._
 >* _Include other details such as attributes and method signatures that you think are needed to support the level of detail in your discussion._
​
### ViewModel Tier
> _**[Sprint 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._
​
> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your ViewModel Tier class diagram 1, etc.](model-placeholder.png)
​
### Model Tier
> _**[Sprint 2, 3 & 4]** Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._
​
> _At appropriate places as part of this narrative provide **one** or more updated and **properly labeled**
> static models (UML class diagrams) with some details such as critical attributes and methods._
> 
![Replace with your Model Tier class diagram 1, etc.](ModelTierSWEN261.png)
​
The model tier is an intermediary between the objects that can be modified by the frontend Angular section and the JSON database. There are two main classes in this tier: User.java and Need.java. User.java represents the individual data of a user, while Need.java represents a need that could be bought by a user. Need.java contains a price, a name which acts as the unique id to prevent duplicates, a tag which can be used to filter out details, and finally a quantity. User.java contains the username, the current items that the user will buy, if it is an admin or not, the total amount of money they spent, and a list of donation rewards they can get given some amount of points. DonationReward.java represents the reward that a user can get, given some amount of money they've spent in total. It contains a name and a number representing the amount of money needed to get it. Event.java contains data pertaining to an event the organization may hold, such as the name and the date and time it will occur.
​
## OO Design Principles
> _**[Sprint 2, 3 & 4]** Will eventually address upto **4 key OO Principles** in your final design. Follow guidance in augmenting those completed in previous Sprints as indicated to you by instructor. Be sure to include any diagrams (or clearly refer to ones elsewhere in your Tier sections above) to support your claims._

<h5>Open/closed</h5>
<p>​
The way the DAO and Controller objects are designed are under the assumption that there will be similarities in how the data is handled. Specifically, for the DAOs, if all objects serialized and deserialized will have a string as a unique identifier and are organized the same way, then there could be a common interface that is used. The controllers which each handle different types of data(User, etc.), on the other hand, all handle the transmission of data via http the same. Therefore each one inherits a common abstract class with all https methods implemented: the only difference is that each class uses a different path for accessing data and themselves for the logger (in order to show which controllers are called).
</p>
​
<h5>Single Responsibility</h5>
​<p>
Each class has a single responsibility. For instance, the User.java class handles data pertaining to individuals who use the website, while the DAO classes each handle deserialization/serialization of a given object. The controller objects each handle a different DAO and link the app to whatever uses the database. Each class in the model tier is purposed to handling some unique data, be it a user, which contains admin permissions, the name, and the items in their basket, or a Need, which has a cost, name, quantity, and type. As a result, the program needs less time to decipher what data it needs to look at.
</p>

<h5>Pure Fabrication</h5>

<p>
Each class in the backend has some specific responsibilty despite not having a defined role in the domain diagram. For instance, the use of controller and DAO (data persistence oriented) classes help abstract the translation of json objects to java objects(and vice versa) from the handling of http requests. This is also exemplified in the front end, where services prevent the repetition of code needed to send http requests and process http responses.
​</p>

​
> _**[Sprint 3 & 4]** OO Design Principles should span across **all tiers.**_
​
## Static Code Analysis/Future Design Improvements
> _**[Sprint 4]** With the results from the Static Code Analysis exercise, 
> **Identify 3-4** areas within your code that have been flagged by the Static Code 
> Analysis Tool (SonarQube) and provide your analysis and recommendations.  
> Include any relevant screenshot(s) with each area._
​
> _**[Sprint 4]** Discuss **future** refactoring and other design improvements your team would explore if the team had additional time._
​
## Testing
> _This section will provide information about the testing performed
> and the results of the testing._
​
<p>
User Stories and Unit Tests: The majority of the user stories were successfully implemented without significant issues. All the unit tests that were executed passed, ensuring the functionality adhered to the predefined criteria. Ufund Manager Story Testing: Testing the ufund manager story demonstrated that all the functionalities met the expected criteria. During live testing, users were able to interact with the cupboard and funding basket, performing operations such as adding items to the funding basket and canceling or reverting actions by clicking outside the designated buttons. Helper Stories Testing: The add and subtract functionalities for the funding basket were tested thoroughly, and both met the acceptance criteria. Live testing confirmed that users could add multiple needs to the funding basket and remove them accordingly. Both the subtract and add operations supported quantity increments and decrements as expected. User Login and Logout Testing: The user login, sign-up, and logout functionalities were found to be satisfactory, considering that the input was not entirely composed of whitespace. The sign-up process ignored usernames that already existed, while the login process verified the credentials against existing records. Successful login granted users access to the funding basket and associated functionalities, and the logout process effectively restricted access to the account until re-login. Search Functionality Testing: The search functionality successfully filtered results from the original list of fundable items, as expected. The fundable items were displayed in a list grid format, consistent with the predefined acceptance criteria. Checkout Functionality Issue: While the checkout functionality seemed to meet the criteria during unit testing, there was an issue with updating or refreshing the funding basket after checkout in the live testing phase. This issue requires further investigation to ensure the funding basket is appropriately updated and refreshed post-checkout.
</p>

​
​
### Acceptance Testing
> _**[Sprint 2 & 4]** Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._
​

<p>
The majority of the user stories passed without much issues and all the unit tests that we tested passed. For the ufund manager story, all of them performed to our expectations. During our live testing of the website the, user/helper was able to interact with the need in the cupboard to add it to the funding basket as well as any functionality such as cancelling/reverting the operation of moving the needs by clicking anywhere but the button responsible for moving it to the funding basket. The helper stories, add and subtract to/from funding basket also met our acceptance criteria and in live testing on the webpage, add and subtract performed to our expectations, the add was able to add multiple needs to the funding basket and the subtract was able to take them out. Both the subtract and the add worked with quantity increment and decrements. The user log in, sign up, and logout stories also performed sufficiently in the case that the input was not entirely compose of whitespace. In the event that a username already existed the sign up would ignore it. The log in would match the credentials of with existing ones in the system otherwise it would ignore them. Once logged in the user had access to the funding basket to add needs, remove needs as well as any functionality given to users. The live testing confirmed that the log out worked and made sure that the user had lost access to their account as well as any functionality tied to having an account until they logged back in. Our search story also performed to our expectations since it would filter out results from our original list of fundable things. The fundable things were also displayed in a list grid format as consistent with our acceptance criteria. However, while the checkout functionality seemed to meet the criteria for the checkout story in unit testing there was an issue with updating/refreshing the funding basket after checkout (where checkout consisted of clearing the funding basket) during live testing
</p>

​

​
### Unit Testing and Code Coverage
> _**[Sprint 4]** Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets._
​
​
>_**[Sprint 2 & 4]** **Include images of your code coverage report.** If there are any anomalies, discuss
> those._
​
![Code coverage for the controller tier](tests/controllerTierTests.png)
​
![Code coverage for the persistence tier](tests/persistenceTierTests.png)
​The persistence tests do not test for failures. We did not think this was needed, given they work anyways in the live testing area.
![Code coverage for the model tier](tests/modelTierTests.png)
Most of the uncovered code are the getters and setters in the Need.java object. Our group thought that if one getter worked, then the others would, given all the untested ones handled primitive values. As of writing, the cupboard.java object has been removed. A branch involving two objects not being equal in the equals function of User.java isn't covered either, mainly because they don't have anythign in particular outside of a return value. As of now, the charge object does not have any tests.