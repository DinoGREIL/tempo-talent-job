-- Active: 1698060232258@@127.0.0.1@5432@postgres

CREATE TABLE
    "country" (
        "id" serial PRIMARY KEY,
        "name" varchar(50)
    );

CREATE TABLE
    "city" (
        "id" bigserial PRIMARY KEY,
        "name" varchar(50),
        "country_id" integer REFERENCES "country" ("id")
    );

CREATE TABLE
    "address" (
        "id" uuid PRIMARY KEY,
        "num" integer,
        "street" text NOT NULL,
        "complement" text,
        "zip_code" integer,
        "city_id" integer REFERENCES "city" ("id")
    );

CREATE TABLE
    "company" (
        "siret" integer PRIMARY KEY,
        "name" varchar,
        "address" uuid REFERENCES "address" ("id")
    );

CREATE TABLE
    "company_recruiter" (
        "id" uuid PRIMARY KEY,
        "company" integer REFERENCES "company" ("siret"),
        "recruiter" uuid,
        "proof" bytea,
        UNIQUE ("company", "recruiter")
    );

CREATE TABLE
    "feature" (
        "id" serial PRIMARY KEY,
        "name" varchar
    );

CREATE TABLE
    "tier" (
        "id" serial PRIMARY KEY,
        "level" varchar,
        "price_biyearly" integer,
        "price_yearly" integer,
        "price_monthly" integer
    );

CREATE TABLE
    "tier_feature" (
        "tier_id" integer REFERENCES "tier" ("id"),
        "feature_id" integer REFERENCES "feature" ("id"),
        "value" varchar(16) PRIMARY KEY ("tier_id", "feature_id")
    );

CREATE TABLE
    "recruiter" (
        "id" uuid PRIMARY KEY,
        "first_name" varchar NOT NULL,
        "last_name" varchar NOT NULL,
        "phone" varchar,
        "email" varchar UNIQUE NOT NULL,
        "password" varchar
    );

CREATE TABLE
    "subscription" (
        "tier_id" integer REFERENCES "tier" ("id"),
        "recruiter" uuid REFERENCES "recruiter" ("id"),
        "start_date" date,
        "end_date",
        PRIMARY KEY ("recruiter", "tier_id")
    );

INSERT INTO "country" ("name")
VALUES ('Afghanistan'), ('Albania'), ('Algeria'), ('Andorra'), ('Angola'), ('Antigua and Barbuda'), ('Argentina'), ('Armenia'), ('Australia'), ('Austria'), ('Azerbaijan'), ('Bahamas'), ('Bahrain'), ('Bangladesh'), ('Barbados'), ('Belarus'), ('Belgium'), ('Belize'), ('Benin'), ('Bhutan'), ('Bolivia'), ('Bosnia and Herzegovina'), ('Botswana'), ('Brazil'), ('Brunei'), ('Bulgaria'), ('Burkina Faso'), ('Burundi'), ('Cabo Verde'), ('Cambodia'), ('Cameroon'), ('Canada'), ('Central African Republic'), ('Chad'), ('Chile'), ('China'), ('Colombia'), ('Comoros'), (
        'Congo, Democratic Republic of the'
    ), ('Congo, Republic of the'), ('Costa Rica'), ('Cote Ivoire'), ('Croatia'), ('Cuba'), ('Cyprus'), ('Czech Republic'), ('Denmark'), ('Djibouti'), ('Dominica'), ('Dominican Republic'), ('Ecuador'), ('Egypt'), ('El Salvador'), ('Equatorial Guinea'), ('Eritrea'), ('Estonia'), (
        'Eswatini (formerly Swaziland)'
    ), ('Ethiopia'), ('Fiji'), ('Finland'), ('France'), ('Gabon'), ('Gambia'), ('Georgia'), ('Germany'), ('Ghana'), ('Greece'), ('Grenada'), ('Guatemala'), ('Guinea'), ('Guinea-Bissau'), ('Guyana'), ('Haiti'), ('Honduras'), ('Hungary'), ('Iceland'), ('India'), ('Indonesia'), ('Iran'), ('Iraq'), ('Ireland'), ('Israel'), ('Italy'), ('Jamaica'), ('Japan'), ('Jordan'), ('Kazakhstan'), ('Kenya'), ('Kiribati'), ('Kosovo'), ('Kuwait'), ('Kyrgyzstan'), ('Laos'), ('Latvia'), ('Lebanon'), ('Lesotho'), ('Liberia'), ('Libya'), ('Liechtenstein'), ('Lithuania'), ('Luxembourg'), ('Madagascar'), ('Malawi'), ('Malaysia'), ('Maldives'), ('Mali'), ('Malta'), ('Marshall Islands'), ('Mauritania'), ('Mauritius'), ('Mexico'), ('Micronesia'), ('Moldova'), ('Monaco'), ('Mongolia'), ('Montenegro'), ('Morocco'), ('Mozambique'), ('Myanmar (formerly Burma)'), ('Namibia'), ('Nauru'), ('Nepal'), ('Netherlands'), ('New Zealand'), ('Nicaragua'), ('Niger'), ('Nigeria'), ('North Korea'), (
        'North Macedonia (formerly Macedonia)'
    ), ('Norway'), ('Oman'), ('Pakistan'), ('Palau'), ('Palestine'), ('Panama'), ('Papua New Guinea'), ('Paraguay'), ('Peru'), ('Philippines'), ('Poland'), ('Portugal'), ('Qatar'), ('Romania'), ('Russia'), ('Rwanda'), ('Saint Kitts and Nevis'), ('Saint Lucia'), (
        'Saint Vincent and the Grenadines'
    ), ('Samoa'), ('San Marino'), ('Sao Tome and Principe'), ('Saudi Arabia'), ('Senegal'), ('Serbia'), ('Seychelles'), ('Sierra Leone'), ('Singapore'), ('Slovakia'), ('Slovenia'), ('Solomon Islands'), ('Somalia'), ('South Africa'), ('South Korea'), ('South Sudan'), ('Spain'), ('Sri Lanka'), ('Sudan'), ('Suriname'), ('Sweden'), ('Switzerland'), ('Syria'), ('Taiwan'), ('Tajikistan'), ('Tanzania'), ('Thailand'), (
        'Timor-Leste (formerly East Timor)'
    ), ('Togo'), ('Tonga'), ('Trinidad and Tobago'), ('Tunisia'), ('Turkey'), ('Turkmenistan'), ('Tuvalu'), ('Uganda'), ('Ukraine'), ('United Arab Emirates'), ('United Kingdom'), ('United States of America'), ('Uruguay'), ('Uzbekistan'), ('Vanuatu'), ('Vatican City (Holy See)'), ('Venezuela'), ('Vietnam'), ('Yemen'), ('Zambia'), ('Zimbabwe');

INSERT INTO
    "city" (name, country_id)
VALUES ('Montpellier', 61), ('Paris', 61), ('Lausanne', 170);

INSERT INTO
    address (
        id,
        num,
        street,
        complement,
        zip_code,
        city_id
    )
VALUES (
        'a1b2c3d4-1234-5678-9012-abcdef123456',
        1,
        'rue de la paix',
        NULL,
        75000,
        1
    ), (
        'b2c3d4e5-1234-5678-9012-abcdef123456',
        2,
        'rue de la joie',
        NULL,
        34000,
        3
    ), (
        'c3d4e5f6-1234-5678-9012-abcdef123456',
        3,
        'rue de la tristesse',
        NULL,
        1000,
        2
    ), (
        'c3d4e526-1234-5678-9012-abcdef123456',
        4,
        'rue de la colère',
        NULL,
        1000,
        2
    );

INSERT INTO
    company(siret, name, address)
VALUES (
        123456789,
        'Company 1',
        'a1b2c3d4-1234-5678-9012-abcdef123456'
    ), (
        987654321,
        'Company 2',
        'b2c3d4e5-1234-5678-9012-abcdef123456'
    ), (
        192837465,
        'Company 3',
        'c3d4e5f6-1234-5678-9012-abcdef123456'
    ), (
        836293720,
        'Tempo Talent',
        'c3d4e526-1234-5678-9012-abcdef123456'
    );

INSERT INTO feature(name) VALUES ('app_per_day'), ('contact_vew') 

INSERT INTO
    tier(
        level,
        price_biyearly,
        price_yearly,
        price_monthly
    )
VALUES ('free', 0, 0, 0), ('basic', 100, 10, 5), ('premium', 200, 90, 10);

INSERT INTO
    tier_feature(tier, feature, value)
VALUES (1, 1, '1'), (1, 2, '1'), (2, 1, '10'), (2, 2, '10'), (3, 1, '100'), (3, 2, '100');

INSERT INTO
    recruiter(
        id,
        first_name,
        last_name,
        phone,
        email,
        password
    )
VALUES (
        'a1b2c3d4-1234-5678-9012-abcdef123456',
        'John',
        'Doe',
        '0123456789',
        'john@doe.com',
        'azerty'
    ), (
        'b2c3d4e5-1234-5678-9012-abcdef123456',
        'Jane',
        'Doe',
        '0123456789',
        'jane@doe.com',
        '123456'
    );

INSERT INTO
    subscription(
        tier_id,
        recruiter_id,
        start_date
    )
VALUES (
        1,
        'a1b2c3d4-1234-5678-9012-abcdef123456',
        '2021-01-01'
    ), (
        2,
        'b2c3d4e5-1234-5678-9012-abcdef123456',
        '2021-01-01'
    );

select s1_0.tier_id,s1_0.recruiter_id,r1_0.id,r1_0.email,r1_0.first_name,r1_0.last_name,r1_0.password,r1_0.phone,s1_0.start_date
from subscription s1_0
left join recruiter r1_0 on r1_0.id=s1_0.recruiter_id
where s1_0.tier_id=?
