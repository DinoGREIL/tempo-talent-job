-- Active: 1698060232258@@127.0.0.1@5432@postgres

CREATE TABLE
    "review" (
        "id" uuid PRIMARY KEY,
        "message" text,
        "rating" decimal,
        "providedAt" date
    );
CREATE TABLE
    "application" (
        "id" uuid PRIMARY KEY,
        
        "job_offer" uuid REFERENCES "job_offer" ("id") ,
        "review" uuid REFERENCES "review" ("id") 
    );
    CREATE TABLE
    "job_offer" (
        "id" uuid PRIMARY KEY,
        "description" text,
        "start" date,
        "end" date,
        "salary" integer,
        "job" integer REFERENCES "job" ("id"),
        
    );
    CREATE TABLE 
    "advantage"(
        "id" serial  PRIMARY KEY ,
        "name" varchar(50)
    );
    CREATE TABLE
    "job"(
        "id" serial PRIMARY KEY,
        "title" varchar(50),
        "category" integer REFERENCES "job_category" ("id")

    );
    CREATE TABLE
    "job_category"(
        "id" serial PRIMARY KEY,
        "name" varchar(100)
    );
    CREATE TABLE 
    "availability"(
        "id" serial PRIMARY KEY,
        "job" integer REFERENCES "job" ("id"),
        "start" date,
        "end" date 
    );
    CREATE TABLE
    "job_offeradvantage"(
        "idjob_offer" uuid REFERENCES REFERENCES "job_offer" ("id"),
        "idadvantage" integer REFERENCES "advantage" ("id")
    ) ;


INSERT INTO "job_category" (id,name) VALUES (1,'Alimentation'),(2,'Service');
INSERT INTO "job" (id,title,category) VALUES (1,'Cuisinier',1);
INSERT INTO "availability"(id,job,start,end) VALUES (1,1,"2021-12-25","2021-12-29");
INSERT INTO "job_offer" (id,description,start,end,salary,job) VALUES ("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11","Cook for me","2021-12-25","2021-12-29",600,1);
INSERT INTO "advantage" (id,name) VALUES (1,"free food");
INSERT INTO "job_offeradvantage" (idjob_offer,idadvantage) VALUES ("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",1);
INSERT INTO "review" (id,rating,message,providedAt) VALUES ("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11",4.5,"very good job","2021-12-29");
INSERT INTO "application" (id,job_offer,review) VALUES ("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11","a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11","a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11");
