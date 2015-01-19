-- -----------------------------------------------------
-- Schema soimmo_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table ACCOMMODATION_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ACCOMMODATION_TYPE (
  ACCOMMODATION_TYPE_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table HEATING_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HEATING_TYPE (
  HEATING_TYPE_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table HOT_WATER_PRODUCTION_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HOT_WATER_PRODUCTION_TYPE (
  HOT_WATER_PRODUCTION_TYPE_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table STATE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS STATE (
  STATE_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table COUNTRY
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS COUNTRY (
  COUNTRY_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table CITY
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CITY (
  CITY_ID INT NOT NULL PRIMARY KEY,
  NAME VARCHAR(45) NULL);


-- -----------------------------------------------------
-- Table ADDRESS
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ADDRESS (
  ADDRESS_ID INT NOT NULL PRIMARY KEY,
  STREET VARCHAR(255) NULL,
  ZIP_CODE VARCHAR(10) NULL,
  CITY_ID INT NOT NULL,
  STATE_ID INT NULL,
  COUNTRY_ID INT NOT NULL,
  LATITUDE DECIMAL(10,6) NULL,
  LONGITUDE DECIMAL(10,6) NULL,
  FOREIGN KEY (STATE_ID) REFERENCES STATE (STATE_ID),
  FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY (COUNTRY_ID),
  FOREIGN KEY (CITY_ID) REFERENCES CITY (CITY_ID));


-- -----------------------------------------------------
-- Table ACCOMMODATION
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ACCOMMODATION (
  ACCOMMODATION_ID INT NOT NULL PRIMARY KEY,
  ADDRESS_ID INT NOT NULL,
  SURFACE DECIMAL(7,2) NULL,
  ROOMS INT NULL,
  FLOOR INT NULL,
  ELEVATOR TINYINT(1) NULL,
  RENT DECIMAL(12,2) NULL,
  CHARGE DECIMAL(12,2) NULL,
  AVAILABILITY DATE NULL,
  ACTIVE TINYINT(1) NULL,
  PUBLISH_WEB TINYINT(1) NULL,
  PUBLISH_SELOGER TINYINT(1) NULL,
  ACCOMMODATION_TYPE_ID INT NOT NULL,
  HEATING_TYPE_ID INT NOT NULL,
  HOT_WATER_PRODUCTION_TYPE_ID INT NOT NULL,
  FOREIGN KEY (ACCOMMODATION_TYPE_ID) REFERENCES ACCOMMODATION_TYPE (ACCOMMODATION_TYPE_ID),
  FOREIGN KEY (HEATING_TYPE_ID) REFERENCES HEATING_TYPE (HEATING_TYPE_ID),
  FOREIGN KEY (HOT_WATER_PRODUCTION_TYPE_ID) REFERENCES HOT_WATER_PRODUCTION_TYPE (HOT_WATER_PRODUCTION_TYPE_ID),
  FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS (ADDRESS_ID));


-- -----------------------------------------------------
-- Table CLIENT
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CLIENT (
  CLIENT_ID INT NOT NULL PRIMARY KEY,
  CIVILITY VARCHAR(45) NULL,
  FIRSTNAME VARCHAR(255) NULL,
  SURNAME VARCHAR(255) NULL,
  MONTHLY_SALARY DECIMAL(9,2) NULL,
  ENDED_TRIAL_PERIOD TINYINT(1) NULL);


-- -----------------------------------------------------
-- Table SEARCH_CRITERIA
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SEARCH_CRITERIA (
  SEARCH_CRITERIA_ID INT NOT NULL PRIMARY KEY,
  MIN_SURFACE DECIMAL(7,2) NULL,
  MIN_ROOMS INT NULL,
  FLOOR INT NULL,
  ELEVATOR INT NULL,
  MAX_RENT_AND_CHARGE DECIMAL(12,2) NULL,
  AVAILABLE_FROM DATE NULL,
  CLIENT_ID INT NOT NULL,
  CITY_ID INT NOT NULL,
  FOREIGN KEY (CLIENT_ID) REFERENCES CLIENT (CLIENT_ID),
  FOREIGN KEY (CITY_ID) REFERENCES CITY (CITY_ID));


-- -----------------------------------------------------
-- Table SEARCH_ACCOMODATION_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SEARCH_ACCOMODATION_TYPE (
  SEARCH_CRITERIA_ID INT NOT NULL,
  ACCOMODATION_TYPE_ID INT NOT NULL,
  FOREIGN KEY (SEARCH_CRITERIA_ID) REFERENCES SEARCH_CRITERIA (SEARCH_CRITERIA_ID),
  FOREIGN KEY (ACCOMODATION_TYPE_ID) REFERENCES ACCOMMODATION_TYPE (ACCOMMODATION_TYPE_ID));


-- -----------------------------------------------------
-- Table SEARCH_HOT_WATER_PRODUCTION_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SEARCH_HOT_WATER_PRODUCTION_TYPE (
  SEARCH_CRITERIA_ID INT NOT NULL,
  HOT_WATER_PRODUCTION_TYPE_ID INT NOT NULL,
  FOREIGN KEY (SEARCH_CRITERIA_ID) REFERENCES SEARCH_CRITERIA (SEARCH_CRITERIA_ID),
  FOREIGN KEY (HOT_WATER_PRODUCTION_TYPE_ID) REFERENCES HOT_WATER_PRODUCTION_TYPE (HOT_WATER_PRODUCTION_TYPE_ID));


-- -----------------------------------------------------
-- Table SEARCH_HEATING_TYPE
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SEARCH_HEATING_TYPE (
  HEATING_TYPE_ID INT NOT NULL,
  SEARCH_CRITERIA_ID INT NOT NULL,
  FOREIGN KEY (HEATING_TYPE_ID) REFERENCES HEATING_TYPE (HEATING_TYPE_ID),
  FOREIGN KEY (SEARCH_CRITERIA_ID) REFERENCES SEARCH_CRITERIA (SEARCH_CRITERIA_ID));
