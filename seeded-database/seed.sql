CREATE TABLE profile 
  ( 
     id                  UUID NOT NULL, 
     age                 INT4, 
     lat                 NUMERIC(12, 8), 
     lon                 NUMERIC(12, 8), 
     NAME                VARCHAR(255), 
     compatibility_score INT8, 
     contacts_exchanged  INT4, 
     display_name        VARCHAR(255), 
     favourite           BOOLEAN NOT NULL, 
     height_in_cm        INT8, 
     job_title           VARCHAR(255), 
     main_photo          VARCHAR(255), 
     religion            VARCHAR(255), 
     PRIMARY KEY (id) 
);

insert into profile(id,display_name,age,job_title,height_in_cm,name,lat,lon,main_photo,contacts_exchanged,favourite,religion) values
('75329ee3-16ea-4c11-acb5-d4df4d247aa7','Caroline',41,'Corporate Lawyer',153,'Leeds',53.801277,-1.548567,'http://thecatapi.com/api/images/get?format=src&type=gif',2,true,'Atheist'),
('e6824163-6d48-4e22-b3b4-96f0f0f744f1','Sharon',47,'Doctor',161,'Solihull',52.412811,-1.778197,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Islam'),
('c9f0b184-a291-45be-88e1-31b6e0f42bae','Natalia',38,'Project Manager',144,'Cardiff',51.481583,-3.17909,'http://thecatapi.com/api/images/get?format=src&type=gif',5,false,'Christian'),
('e6e58f7b-4278-4bc7-813d-2b2ae9e6e627','Marta',55,'Finance',140,'Eastbourne',50.768036,0.290472,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Agnostic'),
('ba12f797-f2e6-42ee-aa78-fb6bf2ab0297','Maria',43,'CEO',175,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('b9a9da9d-b125-426d-beb3-756cb9ca0a6d','Stephanie',39,'Project Manager',153,'London',51.509865,-0.118092,'undefined',4,false,'Christian'),
('9036ed0b-7263-4943-b8b4-a255432f9071','Claire',48,'GP',167,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',6,false,'Atheist'),
('2b530b6d-efce-4931-9e14-c29630c25f5c','Colette',39,'Doctor - Hospital',177,'Swindon',51.568535,-1.772232,'http://thecatapi.com/api/images/get?format=src&type=gif',2,false,'Christian'),
('ea949d28-601c-4553-9dfe-673aaeb1c17a','Caroline',43,'Marketing Consultant',160,'Oxford',51.752022,-1.257677,'http://thecatapi.com/api/images/get?format=src&type=gif',1,false,'Atheist'),
('4a1e4456-bc8b-4faf-924e-8e4416369559','Kate',42,'Psychologist',160,'Salisbury',51.068787,-1.794472,'http://thecatapi.com/api/images/get?format=src&type=gif',10,false,'Buddhist'),
('e95be078-055a-4e6a-b2e9-a7b54deb6bd9','Katie',40,'Lawyer',148,'Weymouth',50.614429,-2.457621,'undefined',0,false,'Atheist'),
('0b052db8-30fd-4ed8-9908-8aa947db8929','Clare',40,'Accountant',144,'Bournemouth',50.720806,-1.904755,'http://thecatapi.com/api/images/get?format=src&type=gif',8,false,'Christian'),
('6b351ee3-b6f6-4544-8cf5-f71f349fb812','Laura',39,'Lawyer',160,'Plymouth',50.376289,-4.143841,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('fe8684fc-819d-4d37-8608-b79d9eb6a3a5','Katlin',39,'Barrister',153,'Inverness',57.477772,-4.224721,'http://thecatapi.com/api/images/get?format=src&type=gif',0,true,'Jewish'),
('896e8ef8-81fa-4833-afae-1a702cfbfbd0','Tracy',39,'Lawyer',153,'Aberdeen',57.149651,-2.099075,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('6e133262-f8e0-417c-a5e8-e144574a7696','Angie',50,'Accountant',153,'Ayr',55.458565,-4.629179,'http://thecatapi.com/api/images/get?format=src&type=gif',8,true,'Atheist'),
('daf3ef06-4c91-4040-ac0f-25710f7b9c8b','Samantha',32,'Project Manager',161,'Belfast',54.607868,-5.926437,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('dc4f8656-8836-4236-9471-7e9e2160d4c0','Elizabeth',41,'Dentist',145,'Londonderry',55.006763,-7.318268,'http://thecatapi.com/api/images/get?format=src&type=gif',4,true,'Islam'),
('e0b0cb5e-4ec3-465f-a3ad-4404152d555a','Emma',40,'Banker',150,'Leeds',53.801277,-1.548567,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('051b0726-31db-4957-ac40-44460bbd8b4d','Diana',44,'Consultant',153,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Atheist'),
('f8ee0809-e080-432a-a5b3-c123623ac588','Kysha',45,'Lawyer',144,'London',51.509865,-0.118092,'undefined',10,true,'Islam'),
('eae47162-39ff-4997-ab6f-8df8278ff79a','Anne',38,'Marketing Consultant',170,'Swindon',51.568535,-1.772232,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Jewish'),
('5c7eb3dd-d8ff-4ea7-9a13-8160e8c5f6cb','Daniela',37,'Doctor',177,'Bournemouth',50.720806,-1.904755,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian'),
('195f854f-d75b-4e45-bbf2-50dc375216e3','Katherine',39,'Lawyer',177,'London',51.509865,-0.118092,'http://thecatapi.com/api/images/get?format=src&type=gif',50,true,'Atheist'),
('78e90c00-9325-4ba5-92ba-5b80044e1b2b','Susan',25,'Project Manager',166,'Harlow',51.772938,0.10231,'http://thecatapi.com/api/images/get?format=src&type=gif',0,false,'Christian')