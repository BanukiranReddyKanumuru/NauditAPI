SET NAMES utf8;
SET
    time_zone = '+00:00';
SET
    sql_mode = 'NO_AUTO_VALUE_ON_ZERO';


CREATE
    DATABASE IF NOT EXISTS `${schema}` DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;

USE
`${schema}`;

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization`
(
    `id`                 char(36)    NOT NULL,
    `name`         varchar(50) NULL,
    `description`          varchar(500) NOT NULL,
    `domain`          varchar(50) NOT NULL,
    `disabled`            boolean     NOT NULL default false,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `org_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`               	char(36)    NOT NULL,
    `name`             varchar(50) NOT NULL DEFAULT 'ROLE_USER',
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `role_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`                 char(36)    NOT NULL,
    `first_name`         varchar(50) NULL,
    `last_name`          varchar(50) NOT NULL,
    `employee_id`        varchar(50) NOT NULL,
    `mobile`       char(12) NOT NULL,
    `join_date`          timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `email`              varchar(50) NOT NULL,
    `password`           varchar(500),
    `role_name`          varchar(500) NOT NULL DEFAULT 'ROLE_USER',
    `role_id`   char(36)  null references role(`id`),
    `disabled`            boolean     NOT NULL default false,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    `organization_id`   char(36) not null references organization(`id`),
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_email_unique` (`email`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user_password_reset_request`;

CREATE TABLE `user_password_reset_request`
(
    `id`                     char(36)    NOT NULL,
    `user_id`                char(36)    NOT NULL,
    `expire_date_time`       timestamp not null,
    `created_date_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `forecast_dash_board`;

CREATE TABLE `forecast_dash_board`
(
    `id`                     char(36)    NOT NULL,
    `organization_id`   char(36) not null references organization(`id`),
    `dash_board_id`          char(36)   NOT NULL,
    `created_date_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `files`;

CREATE TABLE `files`
(
    `id`                     char(36)    NOT NULL,
    `name`         			 varchar(50) NULL,
    `type`          		 varchar(50) NULL,
    `data`          		 MEDIUMBLOB NULL,
     `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer`
(
    `id`                     char(36)    NOT NULL,
    `name`   varchar(50) NULL,
    `created_date_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL     DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    DROP TABLE IF EXISTS `category`;

CREATE TABLE `category`
(
    `id`           char(36)    NOT NULL,
    `name`             varchar(50) NULL,
      `feature_id`   char(36) not null references feature(`id`),
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
     DROP TABLE IF EXISTS `subcategory`;

CREATE TABLE `subcategory`
(
    `id`           char(36)    NOT NULL,
    `name`             varchar(50) NULL,
      `category_id`   char(36) not null references category(`id`),
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    
    DROP TABLE IF EXISTS `controls`;

CREATE TABLE `controls`
(
    `id`           char(36)    NOT NULL,
    `name`             varchar(50) NULL,
    `subcategory_id`     char(36) not null references subcategory(`id`),
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    	
    
     DROP TABLE IF EXISTS `Feature`;

CREATE TABLE `feature`
(
    `id`           char(36)    NOT NULL,
    `name`             varchar(50) NULL,
    `software_id`   char(36) not null references software(`id`),
     `host_location`             varchar(50) NULL,
      `owner`             varchar(50) NULL,
       `review_cycle`             varchar(50) NULL,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    


DROP TABLE IF EXISTS `nist_systems`;

CREATE TABLE `nist_systems`
(
    `id`               	char(36)    NOT NULL,
    `name`             	varchar(50) NOT NULL, 
    `mac_address`        varchar(12)  NULL,
    `os`               	varchar(12) NULL,
    `cpu`            	varchar(12) NULL,
    `ram`           	varchar(6) NULL,
    `hard_disk`     	varchar(6) NULL,
    `user_id`   		char(36) null references user(`id`),
    `manufacture_id`   	char(36) null references manufacturer(`id`),
    `files_id`   		char(36) null references files(`id`),
    `price`    			INT NULL,
    `purchased_date`    DATE NULL,
    `tags`             	varchar(50) NULL,
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
DROP TABLE IF EXISTS `nist_software`;

CREATE TABLE `nist_software`
(
    `id`                     char(36)    NOT NULL,
    `name`         			 varchar(50) NULL,
    `owner`          		 varchar(50) NULL,
    `vendor`          		 varchar(50) NULL,
    `version`          		 varchar(5) NULL,
     `install_date`           timestamp NULL,
     `support`               varchar(50) NULL,
     `license`               varchar(50) NULL,
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `software_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

    CREATE TABLE `nist_systems_software`
(
    `id`                     char(36)    NOT NULL DEFAULT (uuid()),
    `nist_systems_id`   char(36) null references nist_systems(`id`),
    `nist_software_id`   char(36) null references nist_software(`id`),
    `created_date_time`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
    
DROP TABLE IF EXISTS `hr_status`;

CREATE TABLE `hr_status`
(
    `id`               	char(36)    NOT NULL,
    `title`             varchar(50) NOT NULL, 
     `work_date`     DATE NULL,
    `description`       varchar(2000)  NULL,
    `user_id`   		char(36) null references user(`id`),
    `projects_id`   	char(36) null references hr_projects(`id`),
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
    
DROP TABLE IF EXISTS `hr_projects`;

CREATE TABLE `hr_projects`
(
    `id`               	char(36)    NOT NULL,
    `name`             varchar(50) NOT NULL, 
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `hr_projects_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `hr_tasks`;

CREATE TABLE `hr_tasks`
(
    `id`               	char(36)    NOT NULL,
    `name`             varchar(200) NOT NULL,
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `hr_tasks_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `hr_subtasks`;

CREATE TABLE `hr_subtasks`
(
    `id`               	char(36)    NOT NULL,
    `name`             varchar(500) NOT NULL,
    `task_id`   	char(36) null references hr_tasks(`id`),
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `hr_subtasks_name_unique` (`name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `hr_timesheet_attendance`;

CREATE TABLE `hr_timesheet_attendance`
(
    `id`               	char(36)    NOT NULL,
    `task_id`   	char(36) null references hr_tasks(`id`),
    `subtask_id`   	char(36) null references hr_subtasks(`id`),
    `remarks`       varchar(1000)  NULL,
    `user_id`   		char(36) null references user(`id`),
    `projects_id`   	char(36) null references hr_projects(`id`),
    `hours`    			INT NULL,
     `work_date`     DATE NULL,
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department`
(
    `id`               	char(36)    NOT NULL,
    `name`   	char(50) NOT NULL,
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `linkedin_lead`;

CREATE TABLE `linkedin_lead`
(
    `id`               	char(36)    NOT NULL,
    `lead_name`   	char(50) NOT NULL,
    `linkedin_link`   	char(50) NOT NULL,
    `website_link`   	char(50) NULL,
    `response_type`   	char(50) NULL,
    `sent`   	char(50) NULL,
    `user_id`   		char(36) null references user(`id`),
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
     UNIQUE KEY `linkedin_lead_name_unique` (`lead_name`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `linkedin_account`;

CREATE TABLE `linkedin_account`
(
    `id`               	char(36)    NOT NULL,
    `first_name` 		char(50) NOT NULL,
    `last_name` 		char(50) NULL,
    `email`   	char(50) NOT NULL,
    `password`	varchar(50) NOT NULL,
    `type` varchar(50) NULL,
    `user_id`   		char(36) null references user(`id`),
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
     UNIQUE KEY `linkedin_account_email_unique` (`email`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

 DROP TABLE IF EXISTS `crm`;
 
CREATE TABLE `crm`
(
    `id`               	char(36)    NOT NULL,
    `first_name` 		char(50) NOT NULL,
    `last_name` 		char(50) NULL,
    `email`   	char(50) NOT NULL,
    `password`	varchar(50) NOT NULL,
    `type` varchar(50) NULL,
    `join_date`          timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_date_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_updated_date_time` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
     UNIQUE KEY `crm_email_unique` (`email`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
