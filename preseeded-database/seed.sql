create extension earthdistance cascade;

-- https://wiki.postgresql.org/wiki/Distance_in_km
create or replace function distance_in_km (numeric,numeric,numeric,numeric) returns numeric
    AS $$ select ROUND(( ('('||$1||', '||$2||')')::point <@> ('('||$3||', '||$4||')')::point )::NUMERIC * 1.609344) $$
    LANGUAGE SQL
    IMMUTABLE
    RETURNS NULL ON NULL INPUT;

CREATE TABLE profile( id UUID NOT NULL, age INT4, lat NUMERIC(12, 8), lon NUMERIC(12, 8), NAME VARCHAR(255), compatibility_score NUMERIC(3, 2), contacts_exchanged INT4, display_name VARCHAR(255), favourite BOOLEAN NOT NULL, height_in_cm INT8, job_title VARCHAR(255), main_photo VARCHAR(255), religion VARCHAR(255), PRIMARY KEY (id));
insert into profile(id,display_name,age,job_title,height_in_cm,name,lat,lon,main_photo,compatibility_score,contacts_exchanged,favourite,religion) values
('868d1f15-a787-4eb4-a36d-d79182fd9383','Caroline',41,'Corporate Lawyer',153,'Leeds',53.801277,-1.548567,'http://thecatapi.com/api/images/get?format=src&type=gif',0.76,2,true,'Atheist'),
('91683986-87cf-4699-b73d-6cc17479cec5','Sharon',47,'Doctor',161,'Solihull',52.412811,-1.778197,'http://thecatapi.com/api/images/get?format=src&type=gif',0.97,0,false,'Islam'),
('952dd025-8f3e-4f96-922c-8f0ffebd36c3','Natalia',38,'Project Manager',144,'Cardiff',51.481583,-3.17909,'http://thecatapi.com/api/images/get?format=src&type=gif',0.47,5,false,'Christian'),
('52e4f4cb-4247-4f7d-ac4e-cd112bc9e9c4','Marta',55,'Finance',140,'Eastbourne',50.768036,0.290472,'http://thecatapi.com/api/images/get?format=src&type=gif',0.95,0,false,'Agnostic'),
('491e583b-fe0f-4da9-a806-cc58fb7dd0c4','Maria',43,'CEO',175,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0.88,0,false,'Christian'),
('17e03f7b-fd29-4e8c-b583-27b5535ecd26','Stephanie',39,'Project Manager',153,'London',51.509865,-0.118092,null,0.87,4,false,'Christian'),
('b11416c6-e725-4e12-98f5-f8dae4b953d0','Claire',48,'GP',167,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0.83,6,false,'Atheist'),
('c3bd6dd6-22ff-41d5-b789-9f6f37751ceb','Colette',39,'Doctor - Hospital',177,'Swindon',51.568535,-1.772232,'http://thecatapi.com/api/images/get?format=src&type=gif',0.89,2,false,'Christian'),
('e87f803f-cec5-48f8-ae3a-7e32c5327097','Caroline',43,'Marketing Consultant',160,'Oxford',51.752022,-1.257677,'http://thecatapi.com/api/images/get?format=src&type=gif',0.91,1,false,'Atheist'),
('098a8a2a-3574-4a82-bb64-2aa48e396f5a','Kate',42,'Psychologist',160,'Salisbury',51.068787,-1.794472,'http://thecatapi.com/api/images/get?format=src&type=gif',0.97,10,false,'Buddhist'),
('d1e51e14-86ed-487c-8bac-8c18819c42b0','Katie',40,'Lawyer',148,'Weymouth',50.614429,-2.457621,null,0.94,0,false,'Atheist'),
('fa534f7b-f9be-4643-a2d5-24889440e04d','Clare',40,'Accountant',144,'Bournemouth',50.720806,-1.904755,'http://thecatapi.com/api/images/get?format=src&type=gif',0.9,8,false,'Christian'),
('9fadefeb-7b05-4039-ae46-5f1f75c6a208','Laura',39,'Lawyer',160,'Plymouth',50.376289,-4.143841,'http://thecatapi.com/api/images/get?format=src&type=gif',0.89,0,false,'Christian'),
('d1002301-a60d-428c-ac61-06e28443b8f1','Katlin',39,'Barrister',153,'Inverness',57.477772,-4.224721,'http://thecatapi.com/api/images/get?format=src&type=gif',0.87,0,true,'Jewish'),
('6557ea3c-c9aa-43c1-918d-ded9ddbe10b8','Tracy',39,'Lawyer',153,'Aberdeen',57.149651,-2.099075,'http://thecatapi.com/api/images/get?format=src&type=gif',0.87,0,false,'Christian'),
('c76df94a-cf7c-4342-b15d-cac0fa650d70','Angie',50,'Accountant',153,'Ayr',55.458565,-4.629179,'http://thecatapi.com/api/images/get?format=src&type=gif',0.93,8,true,'Atheist'),
('d7d5b9e7-7f6b-4f22-8d1d-07a44c4b76b5','Samantha',32,'Project Manager',161,'Belfast',54.607868,-5.926437,'http://thecatapi.com/api/images/get?format=src&type=gif',0.89,0,false,'Christian'),
('a9d3bf6c-0e1a-4348-8de6-33c30a19df7a','Elizabeth',41,'Dentist',145,'Londonderry',55.006763,-7.318268,'http://thecatapi.com/api/images/get?format=src&type=gif',0.83,4,true,'Islam'),
('980b097a-c6fa-4460-97ef-62463033b40d','Emma',40,'Banker',150,'Leeds',53.801277,-1.548567,'http://thecatapi.com/api/images/get?format=src&type=gif',0.73,0,false,'Christian'),
('a64001d9-0580-4aef-ad77-cb9be310410c','Diana',44,'Consultant',153,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0.5,0,false,'Atheist'),
('f643ecbe-4bc1-4ba7-b9a8-c86ea12a1b20','Kysha',45,'Lawyer',144,'London',51.509865,-0.118092,null,0.88,10,true,'Islam'),
('65cbdaf2-4b52-4efd-ba8d-dd9f5baeb0dd','Anne',38,'Marketing Consultant',170,'Swindon',51.568535,-1.772232,'http://thecatapi.com/api/images/get?format=src&type=gif',0.88,0,false,'Jewish'),
('330b5aa4-b83c-408b-8530-f5d0e70267ca','Daniela',37,'Doctor',177,'Bournemouth',50.720806,-1.904755,'http://thecatapi.com/api/images/get?format=src&type=gif',0.76,0,false,'Christian'),
('5d97a7ae-1b82-4566-bcb6-8507c63a2bab','Katherine',39,'Lawyer',177,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0.99,50,true,'Atheist'),
('3b2e3470-c8f1-44ed-9268-06dd79f0912d','Susan',25,'Project Manager',166,'Harlow',51.772938,0.10231,'http://thecatapi.com/api/images/get?format=src&type=gif',0.88,0,false,'Christian')