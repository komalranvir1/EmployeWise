# EmployeWise

Pagination Added.

Mysql Db Used


Email Configuration Add.


command to run jar file is java -jar EmployeWise.jar


Collection Of Api Url:-


PUT


http://localhost:8080/employee/update/12288cce-961e-4b63-bf27-2cdc82e67e35


﻿
json


{
  "employeeName": "Updated Employee",
  "phoneNumber": "987-654-3210",
  "email": "updated.employee@example.com",
  "reportsTo": "uuid_of_updated_reporting_manager",
  "profileImage": "https://example.com/updated_employee_profile.jpg"
}

GET


http://localhost:8080/employee/get/57c058a1-635c-4888-b91b-1cbbbd00856d

GET


http://localhost:8080/employee/getAll



POST


http://localhost:8080/employee/add



json
{
    "employeeName": "komal",
    "phoneNumber": "7666369197",
    "email": "komalran@example.com",
    "reportsTo": "uuid_of_reporting_manager",
    "profileImage": "https://example.com/komal_profile.jpg"
}
