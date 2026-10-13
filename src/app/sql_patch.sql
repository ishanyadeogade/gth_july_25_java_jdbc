

drop table if exists employee;
create table employee(id serial primary key ,name varchar(50),salary integer,age integer)


drop table if exists student_course_details;
drop table if exists student;
drop table if exists course;

create table student(student_id serial primary key,name varchar(50));
create table course(course_id serial primary key,name varchar(100));
create table student_course_details(student_id int,course_id int,foreign key(student_id) references student(student_id),foreign key(course_id) references course(course_id));

insert into student 
select 1,'Joseph'
union select 2,'Olivia'
union select 3,'Sara'
union select 4,'Levis';

insert into course 
select 1,'Java Full Stack Developer'
union select 2,'Data Engineer'
union select 3,'Cybersecurity'
union select 4,'.NET Devloper';

insert into student_course_details 
select 1,1
union select 1,2
union select 2,4
union select 4,3
union select 3,2;
