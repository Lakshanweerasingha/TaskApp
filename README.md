First Clone the application from this github repo - https://github.com/Lakshanweerasingha/TaskApp.

then after open it in the prefered IDE

change the application.properties as your needs.

then run the application.

to test the api endpoints , use the postman
*http://localhost:8080/api/tasks (POST)
*http://localhost:8080/api/tasks/1 (GET)
*http://localhost:8080/api/tasks/1 (PUT)
*http://localhost:8080/api/tasks/1 (DELETE)
*http://localhost:8080/api/tasks?page=0&size=5&sortBy=title&sortDir=asc (GET - By sorting)

Request body - 
{
  "title": "Task 8",
  "description": "Description of task 1",
  "status": "PENDING"
}

. To run the test cases. run them separately.
