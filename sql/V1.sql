-- Active: 1698060232258@@127.0.0.1@5432@postgres

CREATE TABLE
    "review" (
        "id" uuid PRIMARY KEY,
        "message" text,
        "rating" decimal,
        "providedat" date
    );
    CREATE TABLE
    "job_category"(
        "id" uuid PRIMARY KEY,
        "name" varchar(100)
    );
    CREATE TABLE
    "job"(
        "id" uuid PRIMARY KEY,
        "title" varchar(50),
        "category" uuid REFERENCES "job_category" ("id")

    );
    
    CREATE TABLE
    "job_offer" (
        "id" uuid PRIMARY KEY,
        "description" text,
        "startdate" date,
        "enddate" date,
        "salary" integer,
        "job" uuid REFERENCES "job" ("id")    
    );
CREATE TABLE
    "application" (
        "id" uuid PRIMARY KEY,
        
        "job_offer" uuid REFERENCES "job_offer" ("id") ,
        "review" uuid REFERENCES "review" ("id") 
    );
    
    CREATE TABLE 
    "advantage"(
        "id" uuid  PRIMARY KEY ,
        "name" varchar(50)
    );
    
    CREATE TABLE 
    "availability"(
        "id" uuid PRIMARY KEY,
        "job" uuid REFERENCES "job" ("id"),
        "startdate" date,
        "enddate" date 
    );
    CREATE TABLE
    "job_offeradvantage"(
        "idjob_offer" uuid REFERENCES  "job_offer" ("id"),
        "idadvantage" uuid REFERENCES "advantage" ("id")
    ) ;


INSERT INTO "job_category" (id,name) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','Alimentation'),('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12','Service');
INSERT INTO "job" (id,title,category) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','Cuisinier','a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO "availability"(id,job,"startdate","enddate") VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','2021-12-25','2021-12-29');
INSERT INTO "job_offer" (id,description,"startdate","enddate",salary,job) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','Cook for me','2021-12-25','2021-12-29',600,'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO "advantage" (id,name) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','free food');
INSERT INTO "job_offeradvantage" (idjob_offer,idadvantage) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
INSERT INTO "review" (id,rating,message,providedat) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',4.5,'very good job','2021-12-29');
INSERT INTO "application" (id,job_offer,review) VALUES ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11','a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11');
