/**
* Author: WGL
* Date: 2016-10-31
* Desc: 创建各种组织结构，数据库表
*/
/*==============================================================*/
/* DBMS name:      Sybase SQL Anywhere 12                       */
/* Created on:     2016/10/31 15:06:12                          */
/*==============================================================*/


if exists(select 1 from sys.sysforeignkey where role='FK_INTERVIE_REFERENCE_STUDENT') then
    alter table "Interview application"
       delete foreign key FK_INTERVIE_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INTERVIE_REFERENCE_USER') then
    alter table "Interview application"
       delete foreign key FK_INTERVIE_REFERENCE_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INTERVIE_REFERENCE_STUDENT') then
    alter table "Interview arrangement"
       delete foreign key FK_INTERVIE_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SUCCESSF_REFERENCE_STUDENT') then
    alter table "Successful employment"
       delete foreign key FK_SUCCESSF_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_APPLY FO_REFERENCE_STUDENT') then
    alter table "apply for information"
       delete foreign key "FK_APPLY FO_REFERENCE_STUDENT"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_APPLY FO_REFERENCE_USER') then
    alter table "apply for information"
       delete foreign key "FK_APPLY FO_REFERENCE_USER"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ARRANGE _REFERENCE_CLASS') then
    alter table "arrange course"
       delete foreign key "FK_ARRANGE _REFERENCE_CLASS"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ARRANGE _REFERENCE_COURSE') then
    alter table "arrange course"
       delete foreign key "FK_ARRANGE _REFERENCE_COURSE"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_CLASS_REFERENCE_USER') then
    alter table class
       delete foreign key FK_CLASS_REFERENCE_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DEFERRED_REFERENCE_STUDENT') then
    alter table "deferred employment"
       delete foreign key FK_DEFERRED_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_DEFERRED_REFERENCE_USER') then
    alter table "deferred employment"
       delete foreign key FK_DEFERRED_REFERENCE_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EXAM INF_REFERENCE_CLASS') then
    alter table "exam information"
       delete foreign key "FK_EXAM INF_REFERENCE_CLASS"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_EXAM INF_REFERENCE_COURSE') then
    alter table "exam information"
       delete foreign key "FK_EXAM INF_REFERENCE_COURSE"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_GIVE UP__REFERENCE_USER') then
    alter table "give up_emp"
       delete foreign key "FK_GIVE UP__REFERENCE_USER"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_GIVE UP__REFERENCE_STUDENT') then
    alter table "give up_emp"
       delete foreign key "FK_GIVE UP__REFERENCE_STUDENT"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_INTERVIE_REFERENCE_STUDENT') then
    alter table "interview record"
       delete foreign key FK_INTERVIE_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LEAVE AP_REFERENCE_STUDENT') then
    alter table "leave approve"
       delete foreign key "FK_LEAVE AP_REFERENCE_STUDENT"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_LEAVE AP_REFERENCE_USER') then
    alter table "leave approve"
       delete foreign key "FK_LEAVE AP_REFERENCE_USER"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SCORE_REFERENCE_EXAM INF') then
    alter table score
       delete foreign key "FK_SCORE_REFERENCE_EXAM INF"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SCORE_REFERENCE_STUDENT') then
    alter table score
       delete foreign key FK_SCORE_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_STUDENT_REFERENCE_USER') then
    alter table student
       delete foreign key FK_STUDENT_REFERENCE_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_STUDENT_REFERENCE_CLASS') then
    alter table student
       delete foreign key FK_STUDENT_REFERENCE_CLASS
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TALK REC_REFERENCE_USER') then
    alter table "talk record"
       delete foreign key "FK_TALK REC_REFERENCE_USER"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TALK REC_REFERENCE_STUDENT') then
    alter table "talk record"
       delete foreign key "FK_TALK REC_REFERENCE_STUDENT"
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSFER_REFERENCE_STUDENT') then
    alter table "transfer application"
       delete foreign key FK_TRANSFER_REFERENCE_STUDENT
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_TRANSFER_REFERENCE_CLASS') then
    alter table "transfer application"
       delete foreign key FK_TRANSFER_REFERENCE_CLASS
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_USER_REFERENCE_ROLE') then
    alter table "user"
       delete foreign key FK_USER_REFERENCE_ROLE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_VIOLA RE_REFERENCE_STUDENT') then
    alter table "viola record"
       delete foreign key "FK_VIOLA RE_REFERENCE_STUDENT"
end if;

drop table if exists "Interview application";

drop table if exists "Interview arrangement";

drop table if exists "Successful employment";

drop table if exists "apply for information";

drop table if exists "arrange course";

drop table if exists class;

drop table if exists course;

drop table if exists "deferred employment";

drop table if exists "exam information";

drop table if exists "give up_emp";

drop table if exists "interview record";

drop table if exists "leave approve";

drop table if exists role;

drop table if exists score;

drop table if exists student;

drop table if exists "talk record";

drop table if exists "transfer application";

drop table if exists "user";

drop table if exists "viola record";

/*==============================================================*/
/* Table: "Interview application"                               */
/*==============================================================*/
create table "Interview application" 
(
   Inter_app_ID         integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   inter_appr_state     integer                        null
   	constraint CKC_INTER_APPR_STATE_INTERVIE check (inter_appr_state is null or (inter_appr_state in (待审,审批成功,审批失败))),
   constraint "PK_INTERVIEW APPLICATION" primary key clustered (Inter_app_ID)
);

/*==============================================================*/
/* Table: "Interview arrangement"                               */
/*==============================================================*/
create table "Interview arrangement" 
(
   Inter_arr_ID         integer                        not null,
   sid                  integer                        null,
   "interview company"  varchar(255)                   null,
   "interview job"      varchar(128)                   null,
   "interview date"     date                           null,
   "interview place"    varchar(255)                   null,
   constraint "PK_INTERVIEW ARRANGEMENT" primary key clustered (Inter_arr_ID)
);

/*==============================================================*/
/* Table: "Successful employment"                               */
/*==============================================================*/
create table "Successful employment" 
(
   succ_emp_ID          integer                        not null,
   sid                  integer                        null,
   company              varchar(255)                   null,
   position             varchar(255)                   null,
   "entry time"         date                           null,
   salary               decimal(10,2)                  null,
   constraint "PK_SUCCESSFUL EMPLOYMENT" primary key clustered (succ_emp_ID)
);

/*==============================================================*/
/* Table: "apply for information"                               */
/*==============================================================*/
create table "apply for information" 
(
   leave_ID             integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   "leave start time
   Leave start time
   Leave start time" date                           null,
   "leave end time"     date                           null,
   reason               varchar(102)                   null,
   approval_state       integer                        null
   	constraint "CKC_APPROVAL_STATE_APPLY FO" check (approval_state is null or (approval_state in (待审,审批成功,审批失败))),
   constraint "PK_APPLY FOR INFORMATION" primary key clustered (leave_ID)
);

/*==============================================================*/
/* Table: "arrange course"                                      */
/*==============================================================*/
create table "arrange course" 
(
   "arrange course ID"  integer                        not null,
   class_id             integer                        null,
   "course ID"          integer                        null,
   constraint "PK_ARRANGE COURSE" primary key clustered ("arrange course ID")
);

/*==============================================================*/
/* Table: class                                                 */
/*==============================================================*/
create table class 
(
   class_id             integer                        not null,
   uid                  integer                        null,
   classroom            varchar(64)                    null,
   constraint PK_CLASS primary key clustered (class_id)
);

/*==============================================================*/
/* Table: course                                                */
/*==============================================================*/
create table course 
(
   "course ID"          integer                        not null,
   "course name"        varchar(255)                   null,
   constraint PK_COURSE primary key clustered ("course ID")
);

/*==============================================================*/
/* Table: "deferred employment"                                 */
/*==============================================================*/
create table "deferred employment" 
(
   defe_emp_ID          integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   "deferred date"      date                           null,
   "departure date"     date                           null,
   "back date"          date                           null,
   reason               text                           null,
   "dean's office_state" integer                        null
   	constraint "CKC_DEAN'S OFFICE_STA_DEFERRED" check ("dean's office_state" is null or ("dean's office_state" in (待审,审批成功,审批失败))),
   constraint "PK_DEFERRED EMPLOYMENT" primary key clustered (defe_emp_ID)
);

/*==============================================================*/
/* Table: "exam information"                                    */
/*==============================================================*/
create table "exam information" 
(
   "exam infor ID"      integer                        not null,
   class_id             integer                        null,
   "course ID"          integer                        null,
   "examt date"         date                           null,
   "exam room"          varchar(64)                    null,
   "score analy"        text                           null,
   constraint "PK_EXAM INFORMATION" primary key clustered ("exam infor ID")
);

/*==============================================================*/
/* Table: "give up_emp"                                         */
/*==============================================================*/
create table "give up_emp" 
(
   "give up_emp_ID"     integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   reason               text                           null,
   "dean's office_state" integer                        null
   	constraint "CKC_DEAN'S OFFICE_STA_GIVE UP_" check ("dean's office_state" is null or ("dean's office_state" in (待审,审批成功,审批失败))),
   constraint "PK_GIVE UP_EMP" primary key clustered ("give up_emp_ID")
);

/*==============================================================*/
/* Table: "interview record"                                    */
/*==============================================================*/
create table "interview record" 
(
   "interview id"       integer                        not null,
   sid                  integer                        null,
   "interview state"    integer                        null
   	constraint "CKC_INTERVIEW STATE_INTERVIE" check ("interview state" is null or ("interview state" in (等待通知,面试成功,面试失败))),
   condition            varchar(128)                   null,
   constraint "PK_INTERVIEW RECORD" primary key clustered ("interview id")
);

/*==============================================================*/
/* Table: "leave approve"                                       */
/*==============================================================*/
create table "leave approve" 
(
   "leave approve ID"   integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   "leave date"         date                           null,
   "dean's office_state" integer                        null
   	constraint "CKC_DEAN'S OFFICE_STA_LEAVE AP" check ("dean's office_state" is null or ("dean's office_state" in (待审,审批成功,审批失败))),
   constraint "PK_LEAVE APPROVE" primary key clustered ("leave approve ID")
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role 
(
   role_id              integer                        not null,
   Jurisdiction         varchar(128)                   null,
   "number of person"   integer                        null,
   "description
   
   
   Role description
   
   
   role description" text                           null,
   constraint PK_ROLE primary key clustered (role_id)
);

/*==============================================================*/
/* Table: score                                                 */
/*==============================================================*/
create table score 
(
   "score ID"           integer                        not null,
   "exam infor ID"      integer                        null,
   sid                  integer                        null,
   score                decimal(5,1)                   null,
   assess               text                           null,
   constraint PK_SCORE primary key clustered ("score ID")
);

/*==============================================================*/
/* Table: student                                               */
/*==============================================================*/
create table student 
(
   sid                  integer                        not null,
   uid                  integer                        null,
   class_id             integer                        null,
   school_class         varchar(64)                    null,
   employment_status    varchar(64)                    null,
   student_phone        varchar(32)                    null,
   home_phone           varchar(32)                    null,
   graduated_university varchar(128)                   null,
   graduated_time       date                           null,
   school_major         varchar(128)                   null,
   english_class        varchar(128)                   null
   	constraint CKC_ENGLISH_CLASS_STUDENT check (english_class is null or (english_class in ('四级','六级'))),
   "mail box"           varchar(56)                    null,
   classroom            varchar(128)                   null,
   class                varchar(128)                   null,
   major                varchar(128)                   null,
   "place of origin"    varchar(255)                   null,
   "home address"       varchar(255)                   null,
   "Political landscape" varchar(64)                    null,
   "admission time"     date                           null,
   "end time"           date                           null,
   "late times"         integer                        null,
   "early times
   early times" integer                        null,
   absences             integer                        null,
   "attendance score"   decimal(5,1)                   null,
   constraint PK_STUDENT primary key clustered (sid)
);

/*==============================================================*/
/* Table: "talk record"                                         */
/*==============================================================*/
create table "talk record" 
(
   "talk record_id"     integer                        not null,
   sid                  integer                        null,
   uid                  integer                        null,
   content              text                           null,
   constraint "PK_TALK RECORD" primary key clustered ("talk record_id")
);

/*==============================================================*/
/* Table: "transfer application"                                */
/*==============================================================*/
create table "transfer application" 
(
   "Tran_ app_ID"       integer                        not null,
   sid                  integer                        null,
   class_id             integer                        null,
   "former class state" integer                        null
   	constraint "CKC_FORMER CLASS STAT_TRANSFER" check ("former class state" is null or ("former class state" in (待审,审批成功,审批失败))),
   "new class state"    integer                        null
   	constraint "CKC_NEW CLASS STATE_TRANSFER" check ("new class state" is null or ("new class state" in (待审,审批成功,审批失败))),
   constraint "PK_TRANSFER APPLICATION" primary key clustered ("Tran_ app_ID")
);

/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table "user" 
(
   uid                  integer                        not null,
   role_id              integer                        null,
   name                 varchar(56)                    null,
   sex                  bit                            null,
   birthday             date                           null,
   id                   char(18)                       null,
   email                varchar(128)                   null,
   username             varchar(32)                    null,
   password             varchar(16)                    null,
   constraint PK_USER primary key clustered (uid)
);

/*==============================================================*/
/* Table: "viola record"                                        */
/*==============================================================*/
create table "viola record" 
(
   "viola ID"           integer                        not null,
   sid                  integer                        null,
   rule                 varchar(128)                   null,
   "viola date"         date                           null,
   remark               decimal(5,1)                   null,
   result               text                           null,
   constraint "PK_VIOLA RECORD" primary key clustered ("viola ID")
);

alter table "Interview application"
   add constraint FK_INTERVIE_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "Interview application"
   add constraint FK_INTERVIE_REFERENCE_USER foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "Interview arrangement"
   add constraint FK_INTERVIE_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "Successful employment"
   add constraint FK_SUCCESSF_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "apply for information"
   add constraint "FK_APPLY FO_REFERENCE_STUDENT" foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "apply for information"
   add constraint "FK_APPLY FO_REFERENCE_USER" foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "arrange course"
   add constraint "FK_ARRANGE _REFERENCE_CLASS" foreign key (class_id)
      references class (class_id)
      on update restrict
      on delete restrict;

alter table "arrange course"
   add constraint "FK_ARRANGE _REFERENCE_COURSE" foreign key ("course ID")
      references course ("course ID")
      on update restrict
      on delete restrict;

alter table class
   add constraint FK_CLASS_REFERENCE_USER foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "deferred employment"
   add constraint FK_DEFERRED_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "deferred employment"
   add constraint FK_DEFERRED_REFERENCE_USER foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "exam information"
   add constraint "FK_EXAM INF_REFERENCE_CLASS" foreign key (class_id)
      references class (class_id)
      on update restrict
      on delete restrict;

alter table "exam information"
   add constraint "FK_EXAM INF_REFERENCE_COURSE" foreign key ("course ID")
      references course ("course ID")
      on update restrict
      on delete restrict;

alter table "give up_emp"
   add constraint "FK_GIVE UP__REFERENCE_USER" foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "give up_emp"
   add constraint "FK_GIVE UP__REFERENCE_STUDENT" foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "interview record"
   add constraint FK_INTERVIE_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "leave approve"
   add constraint "FK_LEAVE AP_REFERENCE_STUDENT" foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "leave approve"
   add constraint "FK_LEAVE AP_REFERENCE_USER" foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table score
   add constraint "FK_SCORE_REFERENCE_EXAM INF" foreign key ("exam infor ID")
      references "exam information" ("exam infor ID")
      on update restrict
      on delete restrict;

alter table score
   add constraint FK_SCORE_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table student
   add constraint FK_STUDENT_REFERENCE_USER foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table student
   add constraint FK_STUDENT_REFERENCE_CLASS foreign key (class_id)
      references class (class_id)
      on update restrict
      on delete restrict;

alter table "talk record"
   add constraint "FK_TALK REC_REFERENCE_USER" foreign key (uid)
      references "user" (uid)
      on update restrict
      on delete restrict;

alter table "talk record"
   add constraint "FK_TALK REC_REFERENCE_STUDENT" foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "transfer application"
   add constraint FK_TRANSFER_REFERENCE_STUDENT foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

alter table "transfer application"
   add constraint FK_TRANSFER_REFERENCE_CLASS foreign key (class_id)
      references class (class_id)
      on update restrict
      on delete restrict;

alter table "user"
   add constraint FK_USER_REFERENCE_ROLE foreign key (role_id)
      references role (role_id)
      on update restrict
      on delete restrict;

alter table "viola record"
   add constraint "FK_VIOLA RE_REFERENCE_STUDENT" foreign key (sid)
      references student (sid)
      on update restrict
      on delete restrict;

