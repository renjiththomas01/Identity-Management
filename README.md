# Identity-Management

1. How to run the Identity Management application

a. Start the mongodb and set the data location using administrative command prompt. Sample command is given below:
"C:\Program Files\MongoDB\Server\3.6\bin\mongod.exe" --dbpath C:\workspace\mongodb\data

b. Run the application using Maven command:
mvn clean spring-boot:run

c. Access the Rest api end points from a browser using the swagger url:
http://localhost:8080/swagger-ui.html


2. Rest API end point details

a. CREATE
This rest api creates a new user. Input validations are provided for all the fields. Below given is a sample parameter value:
{ 
  "name": "renjith",
  "userName": "renjiththomas83",
  "password": "pwd",
  "email": "renjiththomas83@gmail.com",
  "userRole": "admin"
}
The user role field should be either 'admin'(admin user) or 'user'(normal user).

Once the user is successfully created, a unique id will be generated(eg : "id": "5ac39eb3ec30dea76e6483ba") which can be used for performing further actions on user.

b. READ
This rest api is used to view the user by providing the unique id generated during the create operation.

c. UPDATE
This will update the fields if any modifications are required. The unique id remains to be same even after the update.

d. DELETE
This rest api will delete the users. It requires the loginUserId(user id of the logged in user who is performing the delete)
 and deleteUserId(id of the user to be deleted). Only admin users are allowed to perform the delete operation.
 
e. LOGIN
This rest api will validate the user name/ password combination. If the credentials are invalid, it will return a 403 error.


3. Scheduler Task to perform an action
A scheduled task is created to display the user names of all the users. The task will run in every minute and will print the 
values in the command prompt. It will display like "The current users are: renjiththomas83;".


4. User permissions for DELETE operation
Following user permissions are added to the DELETE operation:

a. Normal user shouldn’t be able to delete user. For this scenario, following error message will be displayed: 
"Delete user error: Only admin users can delete profiles".

b. Admin is allowed to delete users. For this scenario, the response message will be "User successfully deleted"


5. Docker Container
Tried to containarize with Docker but the Docker build failed to pull the openjdk library. Tried many combinations of system specifications but following error occurred every time:
[INFO] Pulling from library/openjdk
[ERROR] no matching manifest for windows/amd64 in the manifest list entries

The Dockerfile is included in the project.



