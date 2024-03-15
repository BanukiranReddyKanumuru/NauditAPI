
set @ORG_ID = uuid();
INSERT INTO organization
(id, name, description, domain)
VALUES(@ORG_ID, 'Demo Org', 'Demo desc', 'demo-domain');

INSERT INTO forecast_dash_board
(id, organization_id, dash_board_id)
VALUES(uuid(), @ORG_ID, '');

set @ROLE_ID1 = uuid();
INSERT INTO role
(id, name, created_date_time)
VALUES(@ROLE_ID1, 'ROLE_SUPER_ADMIN', CURRENT_TIMESTAMP);

set @ROLE_ID2 = uuid();
INSERT INTO role
(id, name, created_date_time)
VALUES(@ROLE_ID2, 'ROLE_ADMIN', CURRENT_TIMESTAMP);

set @ROLE_ID3 = uuid();
INSERT INTO role
(id, name, created_date_time)
VALUES(@ROLE_ID3, 'ROLE_USER', CURRENT_TIMESTAMP);


set @USER_ID1 = uuid();
set @USER_ID2 = uuid();
set @USER_ID3 = uuid();
INSERT INTO `user`
( id, first_name, last_name, employee_id,mobile,email, password, role_name, organization_id, join_date, role_id)
VALUES(@USER_ID1, 'Bhakta', 'Reddy','ES-001', '9999999999','bhakta@ensarsolutions.com','$2a$10$NelkZq5g0zSXagP9.3wKS.Tw4C5ms5q7hbgIW.qdMIKDPOrKWPvlS', 'ROLE_SUPER_ADMIN', @ORG_ID,'2022-09-16',@ROLE_ID1);

INSERT INTO `user`
( id, first_name, last_name, employee_id,mobile,email, password, role_name, organization_id, join_date,role_id)
VALUES(@USER_ID2, 'Rayudu', 'Chavadam','ES-002','9485214775','rayudu@ensarsolutions.com', '$2a$10$NelkZq5g0zSXagP9.3wKS.Tw4C5ms5q7hbgIW.qdMIKDPOrKWPvlS', 'ROLE_SUPER_ADMIN', @ORG_ID, '2022-09-17',@ROLE_ID2);

INSERT INTO `user`
( id, first_name, last_name, employee_id,mobile,email, password, role_name, organization_id, join_date,role_id)
VALUES(@USER_ID3, 'Sridhar', 'G','ES-003','9485213375','sridharg@ensarsolutions.com', '$2a$10$NelkZq5g0zSXagP9.3wKS.Tw4C5ms5q7hbgIW.qdMIKDPOrKWPvlS', 'ROLE_USER', @ORG_ID, '2022-09-18',@ROLE_ID3);


set @FILE_ID = uuid();
INSERT INTO files(id,name,type,data) VALUES(@FILE_ID,'bhaktainvoice','png', LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/test.png'));

set @MANU_ID1 = uuid();
set @MANU_ID2 = uuid();
INSERT INTO `manufacturer`
(id,name)
VALUES(@MANU_ID1,'Lenovo');


INSERT INTO `manufacturer`
(id,name)
VALUES(@MANU_ID2,'Dell');
set @NIST_SYSTEM_ID1 = uuid();
set @NIST_SYSTEM_ID2 = uuid();
INSERT INTO nist_systems
(id, name, mac_address, os,cpu,ram,hard_disk,purchased_date, user_id,manufacture_id, files_id,price, created_date_time)
VALUES(@NIST_SYSTEM_ID1, 'bhakta', '1271','Windows11','IntelI5','16GB','120GB','2022-09-17', @USER_ID1,@MANU_ID1, @FILE_ID, 1000, CURRENT_TIMESTAMP);

INSERT INTO nist_systems
(id, name, mac_address, os,cpu,ram,hard_disk,purchased_date, user_id,manufacture_id, files_id,price,created_date_time)
VALUES(@NIST_SYSTEM_ID2, 'john', '12712','Windows11','IntelI7','16GB','120GB','2022-09-17',@USER_ID2,@MANU_ID2,  @FILE_ID,1000, CURRENT_TIMESTAMP);


set @NIST_SOFTWARE_ID1 = uuid();
INSERT INTO nist_software
(id, name, owner, vendor, version, install_date, support, license, created_date_time)
VALUES(@NIST_SOFTWARE_ID1, 'msword', 'microsoft','microsoft','2022','2022-09-07','microsoft','microsoft', CURRENT_TIMESTAMP);

set @NIST_SOFTWARE_ID2 = uuid();
INSERT INTO nist_software
(id, name, owner, vendor, version, install_date, support, license, created_date_time)
VALUES(@NIST_SOFTWARE_ID2, 'msexcel', 'microsoft','microsoft','2022','2022-09-07','microsoft','microsoft', CURRENT_TIMESTAMP);

INSERT INTO nist_systems_software
(id, nist_systems_id,nist_software_id, created_date_time)
VALUES(uuid(),@NIST_SYSTEM_ID1,@NIST_SOFTWARE_ID1,  CURRENT_TIMESTAMP);

set @FEATURE_ID = uuid();
INSERT INTO feature
(id, name,software_id,host_location,owner,review_cycle, created_date_time)
VALUES(@FEATURE_ID,'test',@NIST_SOFTWARE_ID1,'asia','test_owner','weekly', CURRENT_TIMESTAMP);

set @CATEGORY_ID = uuid();
INSERT INTO category
(id, name,feature_id, created_date_time)
VALUES(@CATEGORY_ID,'test',@FEATURE_ID, CURRENT_TIMESTAMP);

set @SUBCATEGORY_ID = uuid();
INSERT INTO subcategory
(id, name,category_id, created_date_time)
VALUES(@SUBCATEGORY_ID,'test',@CATEGORY_ID, CURRENT_TIMESTAMP);

set @CONTROLS_ID = uuid();
INSERT INTO controls
(id, name,subcategory_id, created_date_time)
VALUES(@CONTROLS_ID ,'test',@SUBCATEGORY_ID,CURRENT_TIMESTAMP);

set @HR_PROJECTS_ID1 = uuid();
INSERT INTO hr_projects
(id, name, created_date_time)
VALUES(@HR_PROJECTS_ID1, 'LEO', CURRENT_TIMESTAMP);

set @HR_PROJECTS_ID2 = uuid();
INSERT INTO hr_projects
(id, name, created_date_time)
VALUES(@HR_PROJECTS_ID2, 'Freedom', CURRENT_TIMESTAMP);

set @HR_STATUS_ID1 = uuid();
INSERT INTO hr_status
(id, title, description, work_date, user_id,projects_id,created_date_time)
VALUES(@HR_STATUS_ID1, 'fullstack development', 'working on React, Java','2022-09-17',@USER_ID1,@HR_PROJECTS_ID1,CURRENT_TIMESTAMP);

set @HR_STATUS_ID2 = uuid();
INSERT INTO hr_status
(id, title, description, work_date, user_id,projects_id,created_date_time)
VALUES(@HR_STATUS_ID2, 'fullstack development with React', 'working on React, Java','2022-09-17',@USER_ID2,@HR_PROJECTS_ID2,CURRENT_TIMESTAMP); 



set @HR_TASKS_ID1 = uuid();
INSERT INTO hr_tasks
(id, name, created_date_time)
VALUES(@HR_TASKS_ID1, 'Admin', CURRENT_TIMESTAMP);

set @HR_TASKS_ID2 = uuid();
INSERT INTO hr_tasks
(id, name, created_date_time)
VALUES(@HR_TASKS_ID2, 'Salaries', CURRENT_TIMESTAMP);

set @HR_SUBTASKS_ID1 = uuid();
INSERT INTO hr_subtasks
(id, name, task_id, created_date_time)
VALUES(@HR_SUBTASKS_ID1, 'Email Creation', @HR_TASKS_ID1,CURRENT_TIMESTAMP);

set @HR_SUBTASKS_ID2 = uuid();
INSERT INTO hr_subtasks
(id, name, task_id, created_date_time)
VALUES(@HR_SUBTASKS_ID2, 'Payrole process', @HR_TASKS_ID2,CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID1 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID1, @HR_TASKS_ID1, @HR_SUBTASKS_ID1,'in progress',@USER_ID1,@HR_PROJECTS_ID1,4,'2022-09-17',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID2 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID2, @HR_TASKS_ID2, @HR_SUBTASKS_ID2,'Start',@USER_ID2,@HR_PROJECTS_ID2,3,'2022-09-17',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID3 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID3, @HR_TASKS_ID1, @HR_SUBTASKS_ID1,'in progress',@USER_ID2,@HR_PROJECTS_ID2,5,'2022-09-18',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID4 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID4, @HR_TASKS_ID2, @HR_SUBTASKS_ID1,'TODO',@USER_ID2,@HR_PROJECTS_ID1,2,'2022-09-19',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID5 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID5, @HR_TASKS_ID2, @HR_SUBTASKS_ID2,'blocker',@USER_ID2,@HR_PROJECTS_ID2,6,'2022-09-20',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID6 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID6, @HR_TASKS_ID2, @HR_SUBTASKS_ID1,'blocker resolved',@USER_ID2,@HR_PROJECTS_ID1,3.5,'2022-09-21',CURRENT_TIMESTAMP);


set @HR_TIMESHEET_ATTENDANCE_ID7 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID7, @HR_TASKS_ID2, @HR_SUBTASKS_ID1,'test environement',@USER_ID2,@HR_PROJECTS_ID1,2,'2022-09-22',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID8 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID8, @HR_TASKS_ID2, @HR_SUBTASKS_ID1,'waiting for qa response',@USER_ID2,@HR_PROJECTS_ID1,1.5,'2022-09-23',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID9 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID9, @HR_TASKS_ID1, @HR_SUBTASKS_ID1,'development environment',@USER_ID2,@HR_PROJECTS_ID1,8,'2022-09-24',CURRENT_TIMESTAMP);

set @HR_TIMESHEET_ATTENDANCE_ID10 = uuid();
INSERT INTO hr_timesheet_attendance
(id, task_id, subtask_id, remarks, user_id,projects_id,hours, work_date, created_date_time)
VALUES(@HR_TIMESHEET_ATTENDANCE_ID10, @HR_TASKS_ID2, @HR_SUBTASKS_ID1,'production',@USER_ID2,@HR_PROJECTS_ID1,7,'2022-09-25',CURRENT_TIMESTAMP);


set @DEPARTMENT_ID1 = uuid();
INSERT INTO department
(id, name, created_date_time)
VALUES(@DEPARTMENT_ID1, 'Web Development',CURRENT_TIMESTAMP);

set @DEPARTMENT_ID2 = uuid();
INSERT INTO department
(id, name, created_date_time)
VALUES(@DEPARTMENT_ID2, 'Application Development',CURRENT_TIMESTAMP);

set @LINKEDIN_LEAD_ID1 = uuid();
INSERT INTO linkedin_lead
(id,lead_name,linkedin_link, website_link,response_type,sent,user_id, created_date_time)
VALUES(@LINKEDIN_LEAD_ID1, 'Bhakta N','www.linkedin/bhakta', 'www.ensarsolutions.com','Positive', 'Yes',@USER_ID1,CURRENT_TIMESTAMP);

set @LINKEDIN_LEAD_ID2 = uuid();
INSERT INTO linkedin_lead
(id,lead_name,linkedin_link, website_link,response_type,sent,user_id, created_date_time)
VALUES(@LINKEDIN_LEAD_ID2, 'Rayudu Ch','www.linkedin/rchavadam', 'www.delitesoftech.com', 'Positive', 'Yes',@USER_ID2,CURRENT_TIMESTAMP);

set @LINKEDIN_ACCOUNT_ID1 = uuid();
INSERT INTO linkedin_account
(id,first_name,last_name, email,password,type,user_id, created_date_time)
VALUES(@LINKEDIN_ACCOUNT_ID1, 'Sam', 'Ch', 'sales@ensarsolutions.com','Nellore@123','LINKEDIN',@USER_ID1,CURRENT_TIMESTAMP);

set @LINKEDIN_ACCOUNT_ID2 = uuid();
INSERT INTO linkedin_account
(id,first_name,last_name, email, password, type,user_id,created_date_time)
VALUES(@LINKEDIN_ACCOUNT_ID2, 'John', 'Henry','ensarsales@ensarsolutions.com','!Nellore123','EMAIL',@USER_ID2,CURRENT_TIMESTAMP);

set @CRM_ID1 = uuid();
INSERT INTO crm
(id,first_name,last_name, email,password,type,join_date, created_date_time)
VALUES(@CRM_ID1, 'Sam', 'Ch', 'crm@ensarsolutions.com','Nellore@123','LINKEDIN','2022-09-22',CURRENT_TIMESTAMP);

set @CRM_ID2 = uuid();
INSERT INTO crm
(id,first_name,last_name, email, password, type,join_date,created_date_time)
VALUES(@CRM_ID2, 'John', 'Henry','crm2@ensarsolutions.com','!Nellore123','EMAIL','2022-09-22',CURRENT_TIMESTAMP);
