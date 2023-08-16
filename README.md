An API with CRUD in all the entity classes, relationship between the classes classroom and students made for SpringBoot challenge 2

the API consists in creating a class classroom with the array of some students, for create a classroom. 
the classroom cannot start with less than 15 students and more than 30, so, have the status class, for set Status.WAITING or Status.STARTED, that means if classroom is already started or no.
to start the class there must be 1 coordinator, 3 instructors and 1 scrum master
you can create students after the creation of the classroom, putting the "classroom_id" on the json file

*for the application testing:
run the Tests classes

*routes: 
**use v1 or v2 for switch between classroom/squad classes and the others entitys
** in the field {id} you put the if number that you want"
**for create use the api /post; for get use the api /get/{id}, for update use the api /update and for delete use the api /delete?id={id}

CRUD Student -  localhost:8080/v1/scholarship/student/(post, get, put or delete)
                localhost:8080/v1/scholarship/student/get/{id}  
                localhost:8080/v1/scholarship/student/update
                localhost:8080/v1/scholarship/student/delete?id={id} 
CRUD Coordinator -  localhost:8080/v1/scholarship/coordinator/post
                    localhost:8080/v1/scholarship/coordinator/get/1
                    localhost:8080/v1/scholarship/coordinator/update
                    localhost:8080/v1/scholarship/coordinator/delete?id={id} 
CRUD Instructor - localhost:8080/v1/scholarship/instructor/post
                  localhost:8080/v1/scholarship/instructor/get/{id}
                  localhost:8080/v1/scholarship/instructor/update
                  localhost:8080/v1/scholarship/instructor/delete?id={id} 
CRUD Scrum Master - localhost:8080/v1/scholarship/scrumMaster/post
                    localhost:8080/v1/scholarship/scrumMaster/get/{id} 
                    localhost:8080/v1/scholarship/scrumMaster/post
                    localhost:8080/v1/scholarship/scrumMaster/delete?id={id} 
CRUD Classroom -  localhost:8080/v2/scholarship/classroom/post
                  localhost:8080/v2/scholarship/classroom/get/{id} 
                  localhost:8080/v2/scholarship/classroom/update
                  localhost:8080/v2/scholarship/classroom/delete?id={id}
CRUD Squad -  localhost:8080/v2/scholarship/squad/post
              localhost:8080/v2/scholarship/squad/get/{id} 
              localhost:8080/v2/scholarship/squad/update
              localhost:8080/v2/scholarship/squad/delete?id={id} 

For each crud you expect the following results: 
(using the classroom class)

POST : "Classroom saved:

GET : {
        "id": 1,
        "numberOfStudents": 14,
        "numberOfCoordinators": 1,
        "numberOfInstructors": 3,
        "numberOfScrumMasters": 1,
        "status": "STARTED",
        "discipline": "English",
        "students": [
            {
                "id": 1,
                "firstName": "Lucas",
                "lastName": "Pascoal",
                "email": "lucas@gmail.com",
                "address": "Rua João Meirelles, 32"
            }
        ]
    }

PUT : {
        "id": 1,
        "numberOfStudents": 14,
        "numberOfCoordinators": 1,
        "numberOfInstructors": 3,
        "numberOfScrumMasters": 1,
        "status": "STARTED",
        "discipline": "Math",
        "students": [
            {
                "id": 1,
                "firstName": "Lucas",
                "lastName": "Pascoal",
                "email": "lucas@gmail.com",
                "address": "Rua João Meirelles, 32"
            }
          ]
        }

DELETE: "Classroom deleted!"













                    
